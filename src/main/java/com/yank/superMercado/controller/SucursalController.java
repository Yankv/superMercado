package com.yank.superMercado.controller;

import java.net.URI;
import java.util.List;

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
        SucursalDto sucursal = sucursalService.crearSucursal(sucursalDto);
        return ResponseEntity.created(URI.create("/api/" + sucursal.getId()))
                .body(sucursal);
    }

    @GetMapping
    public ResponseEntity<List<SucursalDto>> obtenerSucursales() {
        return ResponseEntity.ok(sucursalService.obtenerSucursales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SucursalDto> obtenerSucursalPorId(@PathVariable Long id) {
        return ResponseEntity.ok(sucursalService.obtenerSucursalPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id) {
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> actualizarSucursal(@PathVariable Long id,
            @Valid @RequestBody SucursalDto sucursalDto) {
        SucursalDto sucursalActualizada = sucursalService.actualizarSucursal(id, sucursalDto);
        return ResponseEntity.ok(sucursalActualizada);
    }
}
