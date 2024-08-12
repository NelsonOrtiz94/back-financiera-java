package com.entidad.financiera.service.impl;

import com.entidad.financiera.entity.Cuenta;
import com.entidad.financiera.entity.Transaccion;
import com.entidad.financiera.exception.ClienteNotFoundException;
import com.entidad.financiera.repository.CuentaRepository;
import com.entidad.financiera.repository.TransaccionRepository;
import com.entidad.financiera.service.TransaccionServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImpl implements TransaccionServiceI {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Transaccion crearTransaccion(String tipoCuenta, Double saldo, String numeroCuentaOrigen, String numeroCuentaDestino) {
        Optional<Cuenta> cuentaOrigenOpt = cuentaRepository.findByNumeroCuenta(numeroCuentaOrigen);
        Optional<Cuenta> cuentaDestinoOpt = cuentaRepository.findByNumeroCuenta(numeroCuentaDestino);

        if (!cuentaOrigenOpt.isPresent()) {
            throw new ClienteNotFoundException.CuentaNoEncontradaException("Cuenta de origen no encontrada");
        }

        if ("Transferencia".equals(tipoCuenta) && !cuentaDestinoOpt.isPresent()) {
            throw new ClienteNotFoundException.CuentaNoEncontradaException("Cuenta de destino no encontrada");
        }

        Cuenta cuentaOrigen = cuentaOrigenOpt.get();
        Cuenta cuentaDestino = cuentaDestinoOpt.orElse(null);

        Transaccion transaccion = new Transaccion();
        transaccion.setTipoCuenta(tipoCuenta);
        transaccion.setSaldo(saldo);
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setCuentaOrigen(cuentaOrigen);

        if ("Consignación".equals(tipoCuenta)) {
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() + saldo);
            cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldoDisponible() + saldo);
        } else if ("Retiro".equals(tipoCuenta)) {
            if (cuentaOrigen.getSaldoDisponible() < saldo) {
                throw new RuntimeException("Saldo insuficiente");
            }
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - saldo);
            cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldoDisponible() - saldo);
        } else if ("Transferencia".equals(tipoCuenta)) {
            if (cuentaOrigen.getSaldoDisponible() < saldo) {
                throw new RuntimeException("Saldo insuficiente");
            }
            cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - saldo);
            cuentaOrigen.setSaldoDisponible(cuentaOrigen.getSaldoDisponible() - saldo);
            cuentaDestino.setSaldo(cuentaDestino.getSaldo() + saldo);
            cuentaDestino.setSaldoDisponible(cuentaDestino.getSaldoDisponible() + saldo);
            transaccion.setCuentaDestino(cuentaDestino);
        }

        cuentaRepository.save(cuentaOrigen);
        if (cuentaDestino != null) {
            cuentaRepository.save(cuentaDestino);
        }

        return transaccionRepository.save(transaccion);
    }


    public Transaccion obtenerTransaccionPorId(Long id) {
        Optional<Transaccion> transaccionOpt = transaccionRepository.findById(id);

        if (!transaccionOpt.isPresent()) {
            throw new RuntimeException("Transacción con ID " + id + " no encontrada");
        }
        return transaccionOpt.get();
    }

    public boolean eliminarTransaccion(Long id) {
        System.out.println("Intentando eliminar la transacción con ID: " + id);

        Optional<Transaccion> transaccionOpt = transaccionRepository.findById(id);

        if (transaccionOpt.isPresent()) {
            System.out.println("Transacción encontrada. Procediendo a eliminar.");
            transaccionRepository.deleteById(id);
            return true;
        } else {
            System.out.println("Transacción con ID " + id + " no encontrada. No se pudo eliminar.");
            return false;
        }
    }


    public Transaccion actualizarTransaccion(Long id, String tipoCuenta, Double saldo, String numeroCuentaOrigen, String numeroCuentaDestino) {
        return null;
    }

    public List<Transaccion> listarTransacciones() {
        return transaccionRepository.findAll();
    }
}

