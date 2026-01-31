package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.TipoProveedorDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoProveedor
import erp.base.api.repository.TipoProveedorRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TipoProveedorService(private val tbaTipoProveedorRepository: TipoProveedorRepository) {

    fun createVendorType(tbaTipoProveedorDto: TipoProveedorDto): Mono<TipoProveedor> =
        tbaTipoProveedorRepository.save(TipoProveedor(null, tbaTipoProveedorDto.nombreTipoProv))

    fun getVendorTypeById(vendorTypeId: Int): Mono<TipoProveedor> =
        tbaTipoProveedorRepository.findById(vendorTypeId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VENDOR_TYPE_NOT_FOUND + vendorTypeId)))

    fun getAllVendorTypes(): Flux<TipoProveedor> =
        tbaTipoProveedorRepository.findAll()

    fun deleteVendorType(vendorTypeId: Int): Mono<Void> =
        tbaTipoProveedorRepository.findById(vendorTypeId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.VENDOR_TYPE_NOT_FOUND + vendorTypeId)))
            .then(tbaTipoProveedorRepository.deleteById(vendorTypeId))
}
