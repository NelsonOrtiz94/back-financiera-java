package com.entidad.financiera.service.impl;

import com.entidad.financiera.entity.Cuenta;
import com.entidad.financiera.repository.CuentaRepository;
import com.entidad.financiera.service.CuentaServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaServiceI {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta obtenerCuentaPorId(Long id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        return cuenta.orElse(null);
    }

    @Override
    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta actualizarCuenta(Long id, Cuenta cuenta) {
        Optional<Cuenta> cuentaExistente = cuentaRepository.findById(id);
        if (cuentaExistente.isPresent()) {
            Cuenta cuentaActualizada = cuentaExistente.get();
            cuentaActualizada.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaActualizada.setSaldo(cuenta.getSaldo());
            cuentaActualizada.setSaldoDisponible(cuenta.getSaldoDisponible());
            return cuentaRepository.save(cuentaActualizada);
        } else {
            return null;
        }
    }

    @Override
    public boolean eliminarCuenta(Long id) {
        if (cuentaRepository.existsById(id)) {
            cuentaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

