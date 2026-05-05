package com.yank.superMercado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yank.superMercado.dto.VentaDto;
import com.yank.superMercado.mapper.DetalleVentaMapper;
import com.yank.superMercado.mapper.VentaMapper;
import com.yank.superMercado.model.DetalleVenta;
import com.yank.superMercado.model.Producto;
import com.yank.superMercado.repository.ProductoRepository;
import com.yank.superMercado.repository.SucursalRepository;
import com.yank.superMercado.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService implements IVentaService {
    private final VentaRepository ventaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;
    private final VentaMapper ventaMapper;
    private final DetalleVentaMapper detalleVentaMapper;

    @Override
    public VentaDto crearVenta(VentaDto ventaDto) {
        // Mapear el DTO a la entidad
        var venta = ventaMapper.toEntity(ventaDto);
        // Obtener la sucursal por su ID y asignarla a la venta
        var sucursal = sucursalRepository.findById(ventaDto.getIdSucursal())
                .orElseThrow(
                        () -> new RuntimeException("No se encontró la sucursal con ID: " + ventaDto.getIdSucursal()));
        venta.setSucursal(sucursal);

        if (ventaDto.getDetalles() != null) {
            // Mapear los detalles de venta del DTO a la entidad
            var detalles = ventaDto.getDetalles().stream()
                    .map(detalleDto -> {
                        DetalleVenta detalle = detalleVentaMapper.toEntity(detalleDto);

                        // Buscar el producto por su nombre
                        Producto producto = productoRepository.findByNombre(detalleDto.getProductoNombre())
                                .orElseThrow(() -> new RuntimeException(
                                        "No se encontró el producto con nombre: " + detalleDto.getProductoNombre()));

                        detalle.setProducto(producto); // Asignar el producto al detalle
                        detalle.setVenta(venta); // Asignar la venta al detalle
                        return detalle;
                    })
                    .toList();

            // Asignar la lista de detalles a la venta
            venta.setDetalles(detalles);
        }
        // Guardar la venta en la base de datos y retornar el DTO resultante
        return ventaMapper.toDto(ventaRepository.save(venta));
    }

    @Override
    public List<VentaDto> traerVentas() {
        // Obtener todas las ventas de la base de datos, mapearlas a DTOs y retornarlas
        return ventaMapper.toDtoList(ventaRepository.findAll());
    }

    @Override
    public VentaDto traerVentaPorId(Long id) {
        // Obtener la venta por su ID, mapearla a un DTO y retornarla
        var venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la venta con ID: " + id));
        return ventaMapper.toDto(venta);
    }

    @Override
    public VentaDto actualizarVenta(Long id, VentaDto ventaDto) {
        // Verificar si existe la venta a actualizar
        var ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la venta con ID: " + id));

        // Mapear los campos actualizables del DTO a la entidad existente
        ventaExistente.setFecha(ventaDto.getFecha());
        ventaExistente.setEstado(ventaDto.getEstado());
        ventaExistente.setTotal(ventaDto.getTotal());

        // Guardar la venta actualizada en la base de datos y retornar el DTO resultante
        return ventaMapper.toDto(ventaRepository.save(ventaExistente));
    }

    @Override
    public void eliminarVenta(Long id) {
        // Verificar si existe la venta a eliminar
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la venta con ID: " + id);
        }

        // Si existe, eliminar la venta
        ventaRepository.deleteById(id);
    }

}
