package com.yank.superMercado.service;

import java.util.List;

import com.yank.superMercado.dto.SucursalDto;

public interface ISucursalService {
    /**
     * Crea una nueva sucursal.
     * 
     * @param sucursalDto Los datos de la sucursal a crear.
     * @return La sucursal creada.
     */
    SucursalDto crearSucursal(SucursalDto sucursalDto);

    /**
     * Obtiene todas las sucursales.
     * 
     * @return Una lista con todas las sucursales.
     */
    List<SucursalDto> traerSucursales();

    /**
     * Obtiene una sucursal por su ID.
     * 
     * @param id El ID de la sucursal.
     * @return La sucursal encontrada o null si no se encuentra.
     */
    SucursalDto traerSucursalPorId(Long id);

    /**
     * Actualiza los datos de una sucursal existente.
     * 
     * @param id El ID de la sucursal a actualizar.
     * @param sucursalDto Los nuevos datos de la sucursal.
     * @return La sucursal actualizada.
     */
    SucursalDto actualizarSucursal(Long id, SucursalDto sucursalDto);

    /**
     * Elimina una sucursal por su ID.
     * 
     * @param id El ID de la sucursal a eliminar.
     */
    void eliminarSucursal(Long id);
}
