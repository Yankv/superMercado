package com.yank.superMercado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
    @Mapping(target = "sucursal", ignore = true) // Ignora el campo sucursal, se mapeará manualmente en el servicio
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
}
