package com.yank.superMercado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yank.superMercado.dto.ProductoDto;
import com.yank.superMercado.exception.NotFoundException;
import com.yank.superMercado.mapper.ProductoMapper;
import com.yank.superMercado.model.Producto;
import com.yank.superMercado.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductoService implements IProductoService {
    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) {
        // Mapear el DTO a la entidad
        Producto producto = mapper.toEntity(productoDto);
        // Guardar, mapear y retornar el producto
        return mapper.toDto(repository.save(producto));
    }

    @Override
    public List<ProductoDto> obtenerProductos() {
        // Obtener todos los productos de la base de datos
        List<Producto> productos = repository.findAll();
        // Mapear y retornar los productos a DTOs
        return mapper.toDtoList(productos);
    }

    @Override
    public ProductoDto obtenerProductoPorId(Long id) {
        // Obtener el producto por ID
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró producto con ID: " + id));

        // Mapear y retornar el DTO
        return mapper.toDto(producto);
    }

    @Override
    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto) {
        // Obtener el producto existente
        Producto productoExistente = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró producto con ID: " + id));

        // Actualizar los campos del producto existente con los datos del DTO
        productoExistente.setNombre(productoDto.getNombre());
        productoExistente.setPrecio(productoDto.getPrecio());
        productoExistente.setCategoria(productoDto.getCategoria());
        productoExistente.setStock(productoDto.getStock());

        // Guardar el producto actualizado en la base de datos, mapear y retornar el DTO
        return mapper.toDto(repository.save(productoExistente));
    }

    @Override
    public void eliminarProducto(Long id) {
        // Verificar si existe el producto a eliminar
        if (!repository.existsById(id)) {
            throw new NotFoundException("No se encontró producto con ID: " + id);
        }

        // Si existe, eliminar el producto
        repository.deleteById(id);
    }
}
