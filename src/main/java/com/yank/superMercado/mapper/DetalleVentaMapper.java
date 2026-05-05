package com.yank.superMercado.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.yank.superMercado.dto.DetalleVentaDto;
import com.yank.superMercado.model.DetalleVenta;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {
    /**
     * Convierte un DetalleVenta en un DetalleVentaDto
     * 
     * @param dto
     * @return DetalleVenta
     */
    // Se ignoran los campos que no existen en el DTO
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "venta", ignore = true)
    DetalleVenta toEntity(DetalleVentaDto dto);

    /**
     * Convierte un DetalleVenta a un DetalleVentaDto
     * 
     * @param entity
     * @return DetalleVentaDto
     */
    @Mapping(source = "producto.nombre", target = "productoNombre") // Mapea el nombre del producto al campo productoNombre del DTO
    @Mapping(target = "subTotal", ignore = true) // Ignora el campo subtotal, se calculará en el servicio
    DetalleVentaDto toDto(DetalleVenta entity);

    /**
     * Convierte una lista de DetalleVenta a una lista de DetalleVentaDto
     * 
     * @param entities
     * @return List<DetalleVentaDto>
     */
    List<DetalleVentaDto> toDtoList(List<DetalleVenta> entities);

    // Método para calcular el subtotal después de mapear el detalle de venta a su DTO
    @AfterMapping
    default void calcularSubtotal(DetalleVenta detalle, @MappingTarget DetalleVentaDto dto) {
        if (detalle.getProducto() != null) {
            dto.setSubTotal(detalle.getCantidad() * detalle.getPrecioUnitario());
        }
    }
}
