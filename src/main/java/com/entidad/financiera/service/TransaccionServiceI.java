package com.entidad.financiera.service;

import com.entidad.financiera.entity.Transaccion;

public interface TransaccionServiceI {

    Transaccion crearTransaccion(String tipoCuenta, Double saldo, String numeroCuentaOrigen, String numeroCuentaDestino);

    Transaccion obtenerTransaccionPorId(Long id);

    Transaccion actualizarTransaccion(Long id, String tipoCuenta, Double saldo, String numeroCuentaOrigen, String numeroCuentaDestino);


}

