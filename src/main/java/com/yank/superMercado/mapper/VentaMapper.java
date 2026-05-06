package com.yank.superMercado.mapper;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.yank.superMercado.dto.VentaDto;
import com.yank.superMercado.model.Venta;

@Mapper(componentModel = "spring", uses = { DetalleVentaMapper.class })
public interface VentaMapper {
    /**
     * Convierte un VentaDto en un Venta
     * 
     * @param dto
     * @return Venta
     */
    @Mapping(target = "sucursal", ignore = true)
    Venta toEntity(VentaDto dto);

    /**
     * Convierte un Venta a un VentaDto
     * 
     * @param entity
     * @return VentaDto
     */
    @Mapping(source = "sucursal.id", target = "idSucursal") // Mapea el ID de la sucursal al campo idSucursal del DTO
    VentaDto toDto(Venta entity);

    /**
     * Convierte una lista de Venta a una lista de VentaDto
     * 
     * @param entities
     * @return List<VentaDto>
     */
    List<VentaDto> toDtoList(List<Venta> entities);

    // Método para calcular el total después de mapear el DTO a la entidad
    @AfterMapping
    default void calcularTotal(VentaDto dto, @MappingTarget Venta venta) {
        if (dto.getDetalles() != null) {
            double total = dto.getDetalles().stream()
                    .mapToDouble(detalle -> detalle.getCantidad() * detalle.getPrecioUnitario())
                    .sum();
            venta.setTotal(total);
        }
    }
}
