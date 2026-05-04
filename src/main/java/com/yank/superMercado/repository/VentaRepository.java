package com.yank.superMercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yank.superMercado.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}
