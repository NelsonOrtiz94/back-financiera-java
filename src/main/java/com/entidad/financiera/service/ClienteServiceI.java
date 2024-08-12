package com.entidad.financiera.service;

import com.entidad.financiera.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServiceI {

    Cliente crearCliente(Cliente cliente);

    Cliente modificarCliente(Long id, Cliente cliente);

    boolean eliminarCliente(Long id);

    List<Cliente> listarClientes();

    Optional<Cliente> obtenerClientePorId(Long id);
}
