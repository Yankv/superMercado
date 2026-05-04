package com.yank.superMercado.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDto {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;
    // Datos de la sucursal
    private Long idSucursal;
    // Lista de detalles de venta
    private List<DetalleVentaDto> detalles;
}
    