package com.yank.superMercado.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yank.superMercado.dto.VentaDto;
import com.yank.superMercado.enums.EstadoVenta;
import com.yank.superMercado.exception.NotFoundException;
import com.yank.superMercado.mapper.DetalleVentaMapper;
import com.yank.superMercado.mapper.VentaMapper;
import com.yank.superMercado.model.DetalleVenta;
import com.yank.superMercado.model.Producto;
import com.yank.superMercado.model.Venta;
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
                        () -> new NotFoundException("No se encontró la sucursal con ID: " + ventaDto.getIdSucursal()));
        venta.setSucursal(sucursal);

        if (ventaDto.getDetalles() != null) {
            // Mapear los detalles de venta del DTO a la entidad
            var detalles = ventaDto.getDetalles().stream()
                    .map(detalleDto -> {
                        DetalleVenta detalle = detalleVentaMapper.toEntity(detalleDto);

                        // Buscar el producto por su ID
                        Producto producto = productoRepository.findById(detalleDto.getProductoId())
                                .orElseThrow(() -> new NotFoundException(
                                        "No se encontró el producto con ID: " + detalleDto.getProductoId()));

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
    public List<VentaDto> obtenerVentas() {
        // Obtener todas las ventas de la base de datos, mapearlas a DTOs y retornarlas
        List<Venta> ventas = ventaRepository.findAll();

        if (ventas.isEmpty()) {
            throw new NotFoundException("No se encontraron ventas registradas.");
        }

        return ventaMapper.toDtoList(ventas);
    }

    @Override
    public VentaDto obtenerVentaPorId(Long id) {
        // Obtener la venta por su ID, mapearla a un DTO y retornarla
        var venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la venta con ID: " + id));
        return ventaMapper.toDto(venta);
    }

    @Override
    public List<VentaDto> obtenerVentasPorSucursalYFecha(Long sucursalId, LocalDate fecha) {
        var ventas = ventaRepository.buscarPorSucursalIdYFecha(sucursalId, fecha);
        if (ventas.isEmpty()) {
            throw new NotFoundException(
                    "No se encontraron ventas para la sucursal con ID: " + sucursalId + " en la fecha: " + fecha);
        }
        return ventaMapper.toDtoList(ventas);
    }

    @Override
    public VentaDto actualizarVenta(Long id, VentaDto ventaDto) {
        // Verificar si existe la venta a actualizar
        var ventaExistente = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró la venta con ID: " + id));

        if (ventaDto.getIdSucursal() != null) {
            // Obtener la sucursal por su ID y asignarla a la venta
            var sucursal = sucursalRepository.findById(ventaDto.getIdSucursal())
                    .orElseThrow(
                            () -> new NotFoundException(
                                    "No se encontró la sucursal con ID: " + ventaDto.getIdSucursal()));
            ventaExistente.setSucursal(sucursal);
        }

        // Mapear los campos actualizables del DTO a la entidad existente
        ventaExistente.setFecha(ventaDto.getFecha());
        ventaExistente.setTotal(ventaDto.getTotal());

        // Guardar la venta actualizada en la base de datos y retornar el DTO resultante
        return ventaMapper.toDto(ventaRepository.save(ventaExistente));
    }

    @Override
    public void eliminarVenta(Long id) {
        // Verificar si existe la venta a eliminar
        Venta venta = ventaRepository.findById(id).orElseThrow(() ->
            new NotFoundException("No se encontró la venta con ID: " + id)
        );

        // Si existe, cambiar el estado
        venta.setEstado(EstadoVenta.CANCELADA);
        ventaRepository.save(venta);
    }

}
