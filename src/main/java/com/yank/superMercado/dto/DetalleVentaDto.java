package com.yank.superMercado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDto {
    private Long id;
    private Integer cantidad;
    private String productoNombre;
    private Double precioUnitario;
    private Double subTotal;
}
