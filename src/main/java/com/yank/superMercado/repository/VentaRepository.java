package com.yank.superMercado.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yank.superMercado.model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    /**
     * Busca una venta por el ID de la sucursal y la fecha.
     * @param sucursalId
     * @param fecha
     * @return
     */
    List<Venta> findBySucursalIdAndFecha(Long sucursalId, LocalDate fecha);
}
