package com.yank.superMercado.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yank.superMercado.dto.SucursalDto;
import com.yank.superMercado.exception.NotFoundException;
import com.yank.superMercado.service.ISucursalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sucursales")
@RequiredArgsConstructor
public class SucursalController {
    private final ISucursalService sucursalService;

    @PostMapping
    public ResponseEntity<SucursalDto> crearSucursal(@Valid @RequestBody SucursalDto sucursalDto) {
        try {
            SucursalDto sucursal = sucursalService.crearSucursal(sucursalDto);
            return ResponseEntity.created(URI.create("/api/" + sucursal.getId()))
                    .body(sucursal);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SucursalDto>> obtenerSucursales() {
        try {
            return ResponseEntity.ok(sucursalService.obtenerSucursales());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDto> obtenerSucursalPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(sucursalService.obtenerSucursalPorId(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        try {
            sucursalService.eliminarSucursal(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> actualizarSucursal(@PathVariable Long id,
            @Valid @RequestBody SucursalDto sucursalDto) {
        try {
            SucursalDto sucursalActualizada = sucursalService.actualizarSucursal(id, sucursalDto);
            return ResponseEntity.ok(sucursalActualizada);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
