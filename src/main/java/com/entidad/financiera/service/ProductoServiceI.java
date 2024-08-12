package com.entidad.financiera.service;

import com.entidad.financiera.entity.Cliente;
import com.entidad.financiera.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceI {

    Producto crearProducto(Producto producto);

    Producto modificarProducto(Long id, Producto producto);

    void eliminarProducto(Long id);

    List<Producto> listarProductos();

    Optional<Producto> obtenerProductoPorId(Long id);

}
