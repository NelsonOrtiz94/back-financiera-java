package com.entidad.financiera.service;

import com.entidad.financiera.entity.Cuenta;
import java.util.List;

public interface CuentaServiceI {
    Cuenta crearCuenta(Cuenta cuenta);
    Cuenta obtenerCuentaPorId(Long id);
    List<Cuenta> listarCuentas();
    Cuenta actualizarCuenta(Long id, Cuenta cuenta);
    boolean eliminarCuenta(Long id);
}

