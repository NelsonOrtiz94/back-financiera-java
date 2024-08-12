package com.entidad.financiera.service.impl;

import com.entidad.financiera.entity.Cliente;
import com.entidad.financiera.entity.Producto;
import com.entidad.financiera.repository.ProductoRepository;
import com.entidad.financiera.service.ProductoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductoServiceImpl implements ProductoServiceI {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto producto) {
        // Validar que el tipo de producto sea válido
        String tipoProducto = producto.getTipoCuenta();
        if (!tipoProducto.equalsIgnoreCase("cuenta corriente") && !tipoProducto.equalsIgnoreCase("cuenta de ahorros")) {
            throw new IllegalArgumentException("El tipo de producto debe ser 'cuenta corriente' o 'cuenta de ahorros'");
        }

        // Validar que el producto esté vinculado a un cliente
        Cliente cliente = producto.getCliente();
        if (cliente == null || cliente.getId() == null) {
            throw new IllegalArgumentException("El producto debe estar vinculado a un cliente válido");
        }

        // Validar que el cliente no tenga más de una cuenta de ahorros y una cuenta corriente
        List<Producto> productosCliente = productoRepository.findByCliente_Id(cliente.getId());
        boolean tieneCuentaCorriente = productosCliente.stream().anyMatch(p -> p.getTipoCuenta().equalsIgnoreCase("cuenta corriente"));
        boolean tieneCuentaAhorros = productosCliente.stream().anyMatch(p -> p.getTipoCuenta().equalsIgnoreCase("cuenta de ahorros"));

        if ((tipoProducto.equalsIgnoreCase("cuenta corriente") && tieneCuentaCorriente) ||
                (tipoProducto.equalsIgnoreCase("cuenta de ahorros") && tieneCuentaAhorros)) {
            throw new IllegalArgumentException("El cliente ya tiene una cuenta de este tipo");
        }

        // Generar número de cuenta automáticamente y verificar su unicidad
        String numeroCuenta = generarNumeroCuenta(tipoProducto);
        while (productoRepository.existsByNumeroCuenta(numeroCuenta)) {
            numeroCuenta = generarNumeroCuenta(tipoProducto);
        }
        producto.setNumeroCuenta(numeroCuenta);

        // Validar saldo
        BigDecimal saldo = producto.getSaldo();
        if (saldo == null || saldo.scale() > 0) {
            throw new IllegalArgumentException("El saldo debe ser numérico y no tener decimales");
        }
        if (tipoProducto.equalsIgnoreCase("cuenta de ahorros") && saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo de una cuenta de ahorros no puede ser menor a $0");
        }

        // Guardar el producto en el repositorio
        return productoRepository.save(producto);
    }

    private String generarNumeroCuenta(String tipoProducto) {
        String prefix = tipoProducto.equalsIgnoreCase("cuenta corriente") ? "33" : "53";
        String suffix = String.format("%08d", new Random().nextInt(100000000));
        return prefix + suffix;
    }

    @Override
    public Producto modificarProducto(Long id, Producto producto) {
        Optional<Producto> productoExistenteOpt = productoRepository.findById(id);

        if (productoExistenteOpt.isPresent()) {
            Producto productoExistente = productoExistenteOpt.get();

            // Actualizar campos solo si no son nulos o vacíos
            if (producto.getTipoCuenta() != null) {
                productoExistente.setTipoCuenta(producto.getTipoCuenta());
            }

            if (producto.getSaldo() != null) {
                // Puedes agregar más validaciones aquí si es necesario
                productoExistente.setSaldo(producto.getSaldo());
            }

            if (producto.getExentaGMF() != null) {
                productoExistente.setExentaGMF(producto.getExentaGMF());
            }

            if (producto.getCliente() != null) {
                // Puedes validar que el cliente existe si es necesario
                productoExistente.setCliente(producto.getCliente());
            }

            // No actualizamos la fecha de creación, solo la fecha de modificación
            productoExistente.setFechaModificacion(LocalDate.now());

            // Guardar los cambios en el repositorio
            return productoRepository.save(productoExistente);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    @Override
    public void eliminarProducto(Long id) {
        // Obtener el producto de la base de datos
        Producto producto = productoRepository.findById(id).orElse(null);

        // Lógica para manejar el caso cuando el saldo no es 0
        throw new IllegalStateException("El producto no se puede eliminar porque el saldo no es $0.");
    }


    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }
}
