package com.entidad.financiera.service.impl;

import com.entidad.financiera.entity.Cliente;
import com.entidad.financiera.repository.ClienteRepository;
import com.entidad.financiera.service.ClienteServiceI;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteServiceI {

    //@Autowired
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public Cliente crearCliente(Cliente cliente) {
        String numeroIdentificacion = cliente.getNumeroIdentificacion();
        if (numeroIdentificacion == null || !numeroIdentificacion.matches("\\d{1,12}")) {
            throw new IllegalArgumentException("El número de identificación debe ser numérico y tener máximo 12 dígitos");
        }
        if (cliente.getNombres() == null || cliente.getApellido() == null){
            throw new NullPointerException("Los nombres y apellidos del cliente no pueden ser nulos");
        }
        if (cliente.getNombres().length() < 2 || cliente.getApellido().length() < 2) {
            throw new IllegalArgumentException("Los nombres y apellidos deben tener al menos 2 caracteres");
        }
        String correo = cliente.getCorreoElectronico();
        if (correo == null || !correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            throw new IllegalArgumentException("El correo electrónico no tiene un formato válido");
        }
        // Calcular la edad del cliente
        LocalDate fechaNacimiento = cliente.getFechaNacimiento();
        LocalDate fechaActual = LocalDate.now();
        Period edad = Period.between(fechaNacimiento, fechaActual);

        // Validar que el cliente no sea menor de edad (menor de 18 años)
        if (edad.getYears() < 18) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad para poder ser registrado");
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modificarCliente(Long id, Cliente cliente) {
        // Validar si el cliente existe en la base de datos
        Optional<Cliente> clienteExistenteOptional = clienteRepository.findById(id);
        if (clienteExistenteOptional.isPresent()) {
            Cliente clienteExistente = clienteExistenteOptional.get();

            // Actualizar campos del cliente existente con los nuevos valores
            clienteExistente.setNombres(cliente.getNombres());
            clienteExistente.setApellido(cliente.getApellido());
            clienteExistente.setCorreoElectronico(cliente.getCorreoElectronico());
            clienteExistente.setFechaNacimiento(cliente.getFechaNacimiento());

            // Calcular y establecer la fecha de modificación automáticamente
            clienteExistente.setFechaModificacion(LocalDate.from(LocalDateTime.now()));

            // Guardar el cliente actualizado en el repositorio
            return clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public boolean eliminarCliente(Long id) {
        // Verificar si el cliente existe en la base de datos
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();

            // Verificar si el cliente tiene productos vinculados
//            if (!cliente.getProducto().isEmpty()) {
//                throw new RuntimeException("No se puede eliminar el cliente porque tiene productos vinculados");
//            }

            // Si no tiene productos vinculados, proceder con la eliminación
            clienteRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Cliente no encontrado");
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
    @Override
    public Optional<Cliente> obtenerClientePorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID no puede ser nulo o negativo");
        }

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isEmpty()) {
            throw new NoSuchElementException("No se encontró un cliente con el ID proporcionado");
        }

        return Optional.of(clienteOptional.get());
    }
}



