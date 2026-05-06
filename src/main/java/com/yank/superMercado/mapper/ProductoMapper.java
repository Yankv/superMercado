package com.yank.superMercado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yank.superMercado.dto.ProductoDto;
import com.yank.superMercado.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    /**
     * Convierte un ProductoDto a un Producto
     * 
     * @param dto
     * @return Producto
     */
    @Mapping(target = "detallesVenta", ignore = true)
    Producto toEntity(ProductoDto dto);

    /**
     * Convierte un Producto a un ProductoDto
     * 
     * @param entity
     * @return ProductoDto
     */
    ProductoDto toDto(Producto entity);

    /**
     * Convierte una lista de Producto a una lista de ProductoDto
     * 
     * @param entities
     * @return List<ProductoDto>
     */
    List<ProductoDto> toDtoList(List<Producto> entities);
}
