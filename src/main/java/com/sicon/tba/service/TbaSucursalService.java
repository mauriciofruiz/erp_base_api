package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.controller.InventarioController;
import com.sicon.tba.dto.BodegaDto;
import com.sicon.tba.dto.TbaMenuDto;
import com.sicon.tba.dto.TbaSucursalDto;
import com.sicon.tba.models.Sucursal;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.TbaSucursalRepository;
import com.sicon.tba.repository.custom.TbaSucursalCustom;
import com.sicon.tba.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Clase que contiene los servicios de las sucursales
 */
@Service
@RequiredArgsConstructor
public class TbaSucursalService {

    private final TbaSucursalRepository tbaSucursalRepository;
    private final TbaSucursalCustom tbaSucursalCustom;
    private final InventarioController inventarioController;
    private Consumer<HttpHeaders> httpHeaders;

    /**
     * Método que crea una sucursal
     *
     * @param branchDto Objeto con los datos de la sucursal
     * @return Objeto con los datos de la sucursal creada
     */
    public Mono<Sucursal> createBranch(TbaSucursalDto branchDto, Consumer<HttpHeaders> httpHeaders) {
        return tbaSucursalRepository.findTopByOrderByIdSucursalDesc()
                .defaultIfEmpty(new Sucursal()) // Maneja el caso cuando no hay registros previos
                .flatMap(lastSucursal -> {
                    Integer lastId = lastSucursal != null && lastSucursal.getIdSucursal() != null ? lastSucursal.getIdSucursal() : 0;
                    String nemonic = createNemonic(lastId + 1);
                    String branchCode = createBranchCode(lastId + 1);
                    Sucursal newSucursal = Sucursal.builder()
                            .idCiudad(branchDto.getIdCiudad())
                            .idCategoriaSucursal(branchDto.getIdCategoriaSucursal())
                            .idRegion(branchDto.getIdRegion())
                            .nombreSucursal(branchDto.getNombreSucursal())
                            .nemonicoSucursal(nemonic)
                            .direccionSucursal(branchDto.getDireccionSucursal())
                            .telefonoSucursal(branchDto.getTelefonoSucursal())
                            .telefono2Sucursal(branchDto.getTelefono2Sucursal())
                            .celularSucursal(branchDto.getCelularSucursal())
                            .correoSucursal(branchDto.getCorreoSucursal())
                            .correo2Sucursal(branchDto.getCorreo2Sucursal())
                            .responsableSucursal(branchDto.getResponsableSucursal())
                            .esOutletSucursal(branchDto.getEsOutletSucursal() != null ? branchDto.getEsOutletSucursal() : Boolean.FALSE)
                            .estadoSucursal(branchDto.getEstadoSucursal() != null ? branchDto.getEstadoSucursal() : Boolean.TRUE)
                            .impresionTermicaSucursal(branchDto.getImpresionTermicaSucursal() != null ? branchDto.getImpresionTermicaSucursal() : Boolean.FALSE)
                            .codigoSucursal(branchCode) // Usando el código generado
                            .build();
                    return tbaSucursalRepository.save(newSucursal)
                            .flatMap(sucursal -> {
                                        List<BodegaDto> bodegaDtoList = new ArrayList<>();
                                        for (BodegaDto bodega : branchDto.getBodegaList()) {
                                            bodega.setSucursalId(sucursal.getIdSucursal());
                                            bodegaDtoList.add(bodega);
                                        }
                                        return inventarioController.createWareHouse(bodegaDtoList, httpHeaders)
                                                .then(Mono.just(sucursal))
                                                .onErrorResume(e -> Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
                                    }
                            );
                });
    }

    public String createNemonic(Integer idSucursal) {
        return String.format("S%02d", idSucursal);
    }

    public static String createBranchCode(Integer idSucursal) {
        return String.format("%03d", idSucursal);
    }

    /**
     * Método que actualiza una sucursal
     *
     * @param branchId  Id de la sucursal
     * @param branchDto Objeto con los datos de la sucursal
     * @return Objeto con los datos de la sucursal actualizada
     */
    public Mono<Sucursal> updateBranch(Integer branchId, TbaSucursalDto branchDto, Consumer<HttpHeaders> httpHeaders) {
        return tbaSucursalRepository.findById(branchId)
                .flatMap(existingSucursal -> {
                    existingSucursal.setIdCiudad(branchDto.getIdCiudad());
                    existingSucursal.setIdCategoriaSucursal(branchDto.getIdCategoriaSucursal());
                    existingSucursal.setIdRegion(branchDto.getIdRegion());
                    existingSucursal.setNombreSucursal(branchDto.getNombreSucursal());
                    existingSucursal.setNemonicoSucursal(branchDto.getNemonicoSucursal());
                    existingSucursal.setDireccionSucursal(branchDto.getDireccionSucursal());
                    existingSucursal.setTelefonoSucursal(branchDto.getTelefonoSucursal());
                    existingSucursal.setTelefono2Sucursal(branchDto.getTelefono2Sucursal());
                    existingSucursal.setCelularSucursal(branchDto.getCelularSucursal());
                    existingSucursal.setCorreoSucursal(branchDto.getCorreoSucursal());
                    existingSucursal.setCorreo2Sucursal(branchDto.getCorreo2Sucursal());
                    existingSucursal.setResponsableSucursal(branchDto.getResponsableSucursal());
                    existingSucursal.setEsOutletSucursal(branchDto.getEsOutletSucursal());
                    existingSucursal.setEstadoSucursal(branchDto.getEstadoSucursal());
                    existingSucursal.setImpresionTermicaSucursal(branchDto.getImpresionTermicaSucursal());
                    existingSucursal.setCodigoSucursal(branchDto.getCodigoSucursal());
                    return tbaSucursalRepository.save(existingSucursal)
                            .flatMap(updatedSucursal ->
                                    inventarioController.updateWareHouse(branchDto.getBodegaList(), httpHeaders)
                                            .then(Mono.just(updatedSucursal))
                                            .onErrorResume(e -> Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())))
                            );
                })
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_NOT_FOUND + branchId)));
    }


    /**
     * Método que consulta una sucursal
     *
     * @param branchId Id de la sucursal
     * @return Objeto con los datos de la sucursal consultada
     */
    public Mono<Sucursal> getBranchById(Integer branchId) {
        return tbaSucursalRepository.findById(branchId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_NOT_FOUND + branchId)));
    }

    /**
     * Método que consulta de sucursales por parámetros
     *
     * @param cityId           Id de la ciudad
     * @param regionId         Id de la región
     * @param categoryBranchId Id de la categoría de sucursal
     * @param name             Nombre de la sucursal
     * @param mnemonic         Nemonico de la sucursal
     * @param isOutlet         Es outlet
     * @param isActive         Estado de la sucursal
     * @param isProduction     Es producción
     * @return Lista de sucursales
     */
    public Flux<Sucursal> getBranchesByParams(Integer cityId, Integer regionId, String categoryBranchId, String name, String mnemonic, Boolean isOutlet, Boolean isActive, Boolean isProduction) {
        boolean isSearchByCity = cityId != null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && !isOutlet && !isActive && !isProduction;
        boolean isSearchByRegion = cityId == null && regionId != null && categoryBranchId == null && name == null && mnemonic == null && !isOutlet && !isActive && !isProduction;
        boolean isSearchByCategoryBranch = cityId == null && regionId == null && categoryBranchId != null && name == null && mnemonic == null && !isOutlet && !isActive && !isProduction;
        boolean isSearchByName = cityId == null && regionId == null && categoryBranchId == null && name != null && mnemonic == null && !isOutlet && !isActive && !isProduction;
        boolean isSearchByMnemonic = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic != null && !isOutlet && !isActive && !isProduction;
        boolean isSearchByIsOutlet = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && isOutlet && !isActive && !isProduction;
        boolean isSearchByIsActive = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && !isOutlet && isActive && !isProduction;
        boolean isSearchByProduction = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && !isOutlet && !isActive && isProduction;
        if (isSearchByCity)
            return tbaSucursalRepository.findByIdCiudad(cityId);
        else if (isSearchByRegion)
            return tbaSucursalRepository.findByIdRegion(regionId);
        else if (isSearchByCategoryBranch)
            return tbaSucursalRepository.findByIdCategoriaSucursal(categoryBranchId);
        else if (isSearchByName)
            return tbaSucursalRepository.findByNombreSucursalContaining(name);
        else if (isSearchByMnemonic)
            return tbaSucursalRepository.findByNemonicoSucursal(mnemonic);
        else if (isSearchByIsOutlet)
            return tbaSucursalRepository.findByEsOutletSucursal(isOutlet);
        else if (isSearchByIsActive)
            return tbaSucursalRepository.findByEstadoSucursal(isActive);
        else if (isSearchByProduction)
            return tbaSucursalCustom.getBranchProduction();
        return tbaSucursalRepository.findAll();
    }

    public Flux<Sucursal> getAllSucursales() {
        return tbaSucursalRepository.findByEstadoSucursal(true);
    }

    public Mono<Paginacion<Sucursal>> getRequestBySearchParams(
            Integer cityId,
            Integer regionId,
            String categoryBranchId,
            String name,
            String mnemonic,
            Boolean isOutlet,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<Sucursal> sucursalFlux = tbaSucursalCustom.getBySearchParams(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = tbaSucursalCustom.getCountBySearchParams(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet);
        return total.flatMap(totalCount -> sucursalFlux.collectList().map(sucursalList -> new Paginacion<>(sucursalList, page, limit, totalCount.intValue())));
    }
}
