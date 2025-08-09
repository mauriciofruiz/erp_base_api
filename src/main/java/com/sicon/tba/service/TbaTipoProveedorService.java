package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaTipoProveedorDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.TbaTipoProveedor;
import com.sicon.tba.repository.TbaTipoProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaTipoProveedorService {


    private final TbaTipoProveedorRepository tbaTipoProveedorRepository;

    /**
     * Método que crea un tipo de proveedor
     * @param tbaTipoProveedorDto objeto que contiene los datos del tipo de proveedor
     * @return  Mono<TbaTipoProveedor> objeto que contiene los datos del tipo de proveedor creado
     */
    public Mono<TbaTipoProveedor> createVendorType(TbaTipoProveedorDto tbaTipoProveedorDto){
        return tbaTipoProveedorRepository.save(
                TbaTipoProveedor.builder()
                        .nombreTipoProveedor(tbaTipoProveedorDto.getNombreTipoProv())
                        .build()
                );
    }
    /**
     * Método que obtiene un tipo de proveedor por su id
     * @param vendorTypeId identificador del tipo de proveedor
     * @return Mono<TbaTipoProveedor> objeto que contiene los datos del tipo de proveedor
     */
    public Mono<TbaTipoProveedor> getVendorTypeById(Integer vendorTypeId){
        return tbaTipoProveedorRepository.findById(vendorTypeId)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VENDOR_TYPE_NOT_FOUND + vendorTypeId)
                        )
                );
    }

    /**
     * Método que obtiene todos los tipos de proveedor
     * @return Flux<TbaTipoProveedor> objeto que contiene los datos de todos los tipos de proveedor
     */
    public Flux<TbaTipoProveedor> getAllVendorTypes(){
        return tbaTipoProveedorRepository.findAll();
    }

    /**
     * Método que elimina un tipo de proveedor
     * @param vendorTypeId identificador del tipo de proveedor
     * @return Mono<Void> objeto que contiene los datos del tipo de proveedor eliminado
     */
    public Mono<Void> deleteVendorType(Integer vendorTypeId) {
        return tbaTipoProveedorRepository.findById(vendorTypeId)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VENDOR_TYPE_NOT_FOUND + vendorTypeId))
                )
                .then(tbaTipoProveedorRepository.deleteById(vendorTypeId));
    }
}
