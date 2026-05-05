package com.yank.superMercado.dto;

import java.time.LocalDate;
import java.util.List;

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
public class VentaDto {
    private Long id;

    @NotNull(message = "La fecha de la venta no puede estar vacía")
    private LocalDate fecha;

    @NotBlank(message = "El estado de la venta no puede estar vacío")
    private String estado;

    @NotNull(message = "El total de la venta no puede ser nulo")
    @Positive(message = "El total de la venta debe ser un valor positivo")
    private Double total;

    // Datos de la sucursal
    @NotNull(message = "El ID de la sucursal no puede ser nulo")
    private Long idSucursal;

    // Lista de detalles de venta
    @NotNull(message = "La lista de detalles de venta no puede ser nula")
    private List<DetalleVentaDto> detalles;
}
