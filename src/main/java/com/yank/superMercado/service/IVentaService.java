package com.yank.superMercado.service;

import java.util.List;

import com.yank.superMercado.dto.VentaDto;

public interface IVentaService {
    VentaDto crearVenta(VentaDto ventaDto);
    List<VentaDto> traerVentas();
    VentaDto traerVentaPorId(Long id);
    VentaDto actualizarVenta(Long id, VentaDto ventaDto);
    void eliminarVenta(Long id);
}
