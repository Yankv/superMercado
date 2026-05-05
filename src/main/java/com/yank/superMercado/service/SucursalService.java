package com.yank.superMercado.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yank.superMercado.dto.SucursalDto;
import com.yank.superMercado.mapper.SucursalMapper;
import com.yank.superMercado.model.Sucursal;
import com.yank.superMercado.repository.SucursalRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SucursalService implements ISucursalService {
    private final SucursalRepository repository;
    private final SucursalMapper mapper;

    @Override
    public SucursalDto crearSucursal(SucursalDto sucursalDto) {
        // Mapear el DTO a la entidad
        Sucursal sucursal = mapper.toEntity(sucursalDto);
        // Guardar, mapear y retornar la sucursal
        return mapper.toDto(repository.save(sucursal));
    }

    @Override
    public List<SucursalDto> traerSucursales() {
        // Obtener todas las sucursales de la base de datos
        List<Sucursal> sucursales = repository.findAll();
        // Mapear y retornar las sucursales a DTOs
        return mapper.toDtoList(sucursales);
    }

    @Override
    public SucursalDto traerSucursalPorId(Long id) {
        // Obtener la sucursal por ID
        Sucursal sucursal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró sucursal con ID: " + id));
        // Mapear y retornar el DTO
        return mapper.toDto(sucursal);
    }

    @Override
    public SucursalDto actualizarSucursal(Long id, SucursalDto sucursalDto) {
        // Obtener la sucursal por ID
        Sucursal sucursalExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró sucursal con ID: " + id));

        // Actualizar los datos de la sucursal
        sucursalExistente.setNombre(sucursalDto.getNombre());
        sucursalExistente.setDireccion(sucursalDto.getDireccion());

        // Mapear y retornar el DTO
        return mapper.toDto(repository.save(sucursalExistente));
    }

    @Override
    public void eliminarSucursal(Long id) {
        // Verificar si existe la sucursal a eliminar
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encontró sucursal con ID: " + id);
        }

        // Si existe, eliminar la sucursal
        repository.deleteById(id);
    }
}
