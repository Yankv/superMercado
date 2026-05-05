package com.yank.superMercado.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDto {
    private Long id;

    @NotNull(message = "La cantidad no puede ser nula")
    @Positive(message = "La cantidad debe ser un valor positivo")
    private Integer cantidad;

    @NotBlank(message = "El nombre del producto no puede estar vacío")
    private String productoNombre;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Positive(message = "El precio unitario debe ser un valor positivo")
    private Double precioUnitario;

    @NotNull(message = "El subtotal no puede ser nulo")
    @Positive(message = "El subtotal debe ser un valor positivo")
    private Double subTotal;
}
