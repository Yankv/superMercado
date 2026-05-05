package com.yank.superMercado.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.yank.superMercado.dto.SucursalDto;
import com.yank.superMercado.model.Sucursal;

@Mapper(componentModel = "spring")
public interface SucursalMapper {
    /**
     * Convierte una SucursalDto en una Sucursal
     * 
     * @param dto
     * @return Sucursal
     */
    Sucursal toEntity(SucursalDto dto);

    /**
     * Convierte una Sucursal en una SucursalDto
     * 
     * @param entity
     * @return SucursalDto
     */
    SucursalDto toDto(Sucursal entity);

    /**
     * Convierte una lista de Sucursal a una lista de SucursalDto
     * 
     * @param entities
     * @return List<SucursalDto>
     */
    List<SucursalDto> toDtoList(List<Sucursal> entities);
}
