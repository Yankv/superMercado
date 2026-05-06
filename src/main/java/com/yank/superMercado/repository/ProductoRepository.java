package com.yank.superMercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yank.superMercado.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
