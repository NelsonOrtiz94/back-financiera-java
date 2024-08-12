package com.entidad.financiera;

import com.entidad.financiera.entity.Cliente;
import com.entidad.financiera.repository.ClienteRepository;
import com.entidad.financiera.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombres("Juan");
        cliente.setApellido("Perez");

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteGuardado = clienteServiceImpl.crearCliente(cliente);

        assertEquals(cliente.getNombres(), clienteGuardado.getNombres());
        assertEquals(cliente.getApellido(), clienteGuardado.getApellido());

        verify(clienteRepository, times(1)).save(cliente);
    }

    // Más tests para modificar, eliminar, etc.
}
