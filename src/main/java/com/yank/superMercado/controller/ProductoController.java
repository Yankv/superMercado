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

import com.yank.superMercado.dto.ProductoDto;
import com.yank.superMercado.service.IProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final IProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@Valid @RequestBody ProductoDto productoDto) {
        ProductoDto producto = productoService.crearProducto(productoDto);
        return ResponseEntity.created(URI.create("/api/productos/" + producto.getId()))
                .body(producto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerProductos() {
        List<ProductoDto> productos = productoService.obtenerProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoPorId(@PathVariable Long id) {
        ProductoDto producto = productoService.obtenerProductoPorId(id);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable Long id,
            @Valid @RequestBody ProductoDto productoDto) {
        ProductoDto producto = productoService.actualizarProducto(id, productoDto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
