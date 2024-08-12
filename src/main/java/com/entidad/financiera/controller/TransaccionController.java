package com.entidad.financiera.controller;

import com.entidad.financiera.dto.TransaccionRequest;
import com.entidad.financiera.entity.Transaccion;
import com.entidad.financiera.service.impl.TransaccionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionServiceImpl transaccionService;

    @PostMapping
    public ResponseEntity<Transaccion> crearTransaccion(@RequestBody TransaccionRequest request) {
        Transaccion transaccion = transaccionService.crearTransaccion(request.getTipoCuenta(), request.getSaldo(), request.getNumeroCuentaOrigen(), request.getNumeroCuentaDestino());
        return ResponseEntity.ok(transaccion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> obtenerTransaccion(@PathVariable Long id) {
        Transaccion transaccion = transaccionService.obtenerTransaccionPorId(id);
        if (transaccion != null) {
            return ResponseEntity.ok(transaccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Transaccion>> listarTransacciones() {
        List<Transaccion> transacciones = transaccionService.listarTransacciones();
        return ResponseEntity.ok(transacciones);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaccion> actualizarTransaccion(@PathVariable Long id, @RequestBody TransaccionRequest request) {
        Transaccion transaccion = transaccionService.actualizarTransaccion(id, request.getTipoCuenta(), request.getSaldo(), request.getNumeroCuentaOrigen(), request.getNumeroCuentaDestino());
        if (transaccion != null) {
            return ResponseEntity.ok(transaccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransaccion(@PathVariable Long id) {
        boolean eliminado = transaccionService.eliminarTransaccion(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
