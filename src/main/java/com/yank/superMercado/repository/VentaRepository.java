package com.yank.superMercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yank.superMercado.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}
