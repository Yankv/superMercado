package com.yank.superMercado.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.yank.superMercado.dto.request.ProductoRequest;
import com.yank.superMercado.dto.response.ProductoResponse;
import com.yank.superMercado.model.Producto;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    /**
     * Convierte un objeto ProductoRequest en una entidad Producto.
     * Los campos detallesVenta e id se ignoran ya que se manejan por separado
     * en el proceso de creación o actualización.
     * 
     * @param dto El objeto ProductoRequest que contiene los datos de entrada.
     * @return La entidad Producto resultante de la conversión.
     */
    // Se ignoran los campos que no existen en el DTO
    @Mapping(target = "detallesVenta", ignore = true)
    @Mapping(target = "id", ignore = true)
    Producto toEntity(ProductoRequest dto);

    /**
     * Convierte una entidad Producto en un objeto ProductoResponse.
     * Realiza un mapeo directo de los campos correspondientes.
     * 
     * @param entity La entidad Producto que se va a convertir.
     * @return El objeto ProductoResponse resultante.
     */
    ProductoResponse toDto(Producto entity);

    /**
     * Convierte una lista de entidades Producto en una lista de objetos ProductoResponse.
     * Aplica la conversión individual a cada elemento de la lista.
     * 
     * @param entities La lista de entidades Producto a convertir.
     * @return La lista de objetos ProductoResponse resultantes.
     */
    List<ProductoResponse> toDtoList(List<Producto> entities);
}
