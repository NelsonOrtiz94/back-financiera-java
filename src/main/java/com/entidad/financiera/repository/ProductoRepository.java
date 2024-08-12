package com.entidad.financiera.repository;

import com.entidad.financiera.entity.Cliente;
import com.entidad.financiera.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);

    List<Producto> findByCliente_Id(Long clienteId);
}
