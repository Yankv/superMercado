package com.yank.superMercado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yank.superMercado.model.Sucursal;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    // Método para buscar una sucursal por su nombre
    Optional<Sucursal> findByNombre(String nombre);
}
