package com.yank.superMercado.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yank.superMercado.dto.VentaDto;
import com.yank.superMercado.service.IVentaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {
    private final IVentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaDto> crearVenta(@Valid @RequestBody VentaDto ventaDto) {
        VentaDto venta = ventaService.crearVenta(ventaDto);
        return ResponseEntity.created(URI.create("/api/ventas/" + venta.getId()))
                .body(venta);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VentaDto>> obtenerVentas() {
        List<VentaDto> ventas = ventaService.obtenerVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDto> obtenerVentaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVentaPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<VentaDto>> obtenerVentasPorSucursalYFecha(
            @RequestParam Long sucursalId,
            @RequestParam LocalDate fecha) {
        List<VentaDto> ventas = ventaService.obtenerVentasPorSucursalYFecha(sucursalId, fecha);
        return ResponseEntity.ok(ventas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDto> actualizarVenta(@PathVariable Long id,
            @Valid @RequestBody VentaDto ventaDto) {
        return ResponseEntity.ok(ventaService.actualizarVenta(id, ventaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}
