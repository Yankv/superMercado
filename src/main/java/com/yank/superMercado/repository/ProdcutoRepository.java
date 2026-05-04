package com.yank.superMercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yank.superMercado.model.Producto;

public interface ProdcutoRepository extends JpaRepository<Producto, Long> {
    // Método para buscar un producto por su nombre
    Optional<Producto> findByNombre(String nombre);
}
