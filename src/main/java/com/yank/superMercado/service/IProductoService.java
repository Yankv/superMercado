package com.yank.superMercado.service;

import java.util.List;

import com.yank.superMercado.dto.ProductoDto;

public interface IProductoService {
    /**
     * Crea un nuevo producto a partir de un ProductoDto.
     * 
     * @param productoDto Los datos del prodcuto a crear.
     * @return productoDto creado.
     */
    ProductoDto crearProducto(ProductoDto productoDto);

    /**
     * Obtiene una lista de todos los productos disponibles.
     * 
     * @return Lista con los productos encontrados.
     */
    List<ProductoDto> obtenerProductos();

    /**
     * Obtiene un producto por su ID.
     * 
     * @param id ID del producto a buscar.
     * @return Producto encontrado.
     */
    ProductoDto obtenerProductoPorId(Long id);

    /**
     * Obtiene un producto por su nombre.
     * 
     * @param nombre Nombre del producto a buscar.
     * @return Producto encontrado.
     */
    ProductoDto obtenerProductoPorNombre(String nombre);

    /**
     * Actualiza un producto existente.
     * 
     * @param id ID del producto a actualizar.
     * @param productoDto Datos del producto a actualizar.
     * @return Producto actualizado.
     */
    ProductoDto actualizarProducto(Long id, ProductoDto productoDto);

    /**
     * Elimina un producto por su ID.
     * 
     * @param id ID del producto a eliminar.
     */
    void eliminarProducto(Long id);
}
