package com.yank.superMercado.service;

import java.util.List;

import com.yank.superMercado.dto.VentaDto;

public interface IVentaService {
    /**
     * Crea una nueva venta.
     * 
     * @param ventaDto
     * @return La venta creada.
     */
    VentaDto crearVenta(VentaDto ventaDto);

    /**
     * Obtiene todas las ventas.
     * 
     * @return Una lista con todas las ventas.
     */
    List<VentaDto> obtenerVentas();

    /**
     * Obtiene una venta por su ID.
     * 
     * @param id El ID de la venta.
     * @return La venta encontrada o null si no se encuentra.
     */
    VentaDto obtenerVentaPorId(Long id);

    /**
     * Actualiza los datos de una venta existente.
     * 
     * @param id       El ID de la venta a actualizar.
     * @param ventaDto Los nuevos datos de la venta.
     * @return La venta actualizada.
     */
    VentaDto actualizarVenta(Long id, VentaDto ventaDto);

    /**
     * Elimina una venta por su ID.
     * 
     * @param id El ID de la venta a eliminar.
     */
    void eliminarVenta(Long id);
}
