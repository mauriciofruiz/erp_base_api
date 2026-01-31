package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.FormaPagoCobroDto
import erp.base.api.exception.CustomException
import erp.base.api.models.FormaPagoCobro
import erp.base.api.repository.FormaPagoCobroRepository
import erp.base.api.utils.Paginacion
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.math.BigDecimal

@Service
class FormaPagoCobroService(private val tbaFormaPagoCobroRepository: FormaPagoCobroRepository) {

    fun createPaymentCollectionMethod(tbaFormaPagoCobroDto: FormaPagoCobroDto): Mono<FormaPagoCobro> {
        val existsPaymentCollectionMethod = tbaFormaPagoCobroRepository.existsById(tbaFormaPagoCobroDto.idFormaPagoCobro!!)
        return existsPaymentCollectionMethod.flatMap { exists ->
            if (exists) {
                Mono.error(CustomException(HttpStatus.BAD_REQUEST, ErrorMessage.PAYMENT_COLLECTION_METHOD_ALREADY_EXISTS + tbaFormaPagoCobroDto.idFormaPagoCobro))
            } else {
                tbaFormaPagoCobroRepository.createPaymentCollectionMethod(
                    tbaFormaPagoCobroDto.idFormaPagoCobro!!,
                    tbaFormaPagoCobroDto.nombreFormaPagoCobro!!,
                    tbaFormaPagoCobroDto.aliasFormaPagoCobro ?: "",
                    tbaFormaPagoCobroDto.descuentoFormaPagoCobro ?: BigDecimal.ZERO,
                    tbaFormaPagoCobroDto.idFormaPagoCobro!!,
                    tbaFormaPagoCobroDto.estadoFormaPagoCobro ?: true
                ).then(tbaFormaPagoCobroRepository.findById(tbaFormaPagoCobroDto.idFormaPagoCobro!!))
            }
        }
    }

    fun updatePaymentCollectionMethod(id: String, tbaFormaPagoCobroDto: FormaPagoCobroDto): Mono<FormaPagoCobro> =
        tbaFormaPagoCobroRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PAYMENT_COLLECTION_METHOD_NOT_FOUND + id)))
            .flatMap { tbaFormaPagoCobro ->
                tbaFormaPagoCobro.nombreFormaPagoCobro = tbaFormaPagoCobroDto.nombreFormaPagoCobro
                tbaFormaPagoCobro.aliasFormaPagoCobro = tbaFormaPagoCobroDto.aliasFormaPagoCobro
                tbaFormaPagoCobro.descuentoFormaPagoCobro = tbaFormaPagoCobroDto.descuentoFormaPagoCobro
                tbaFormaPagoCobro.codigoFormaPagoCobro = tbaFormaPagoCobroDto.idFormaPagoCobro
                tbaFormaPagoCobro.estadoFormaPagoCobro = tbaFormaPagoCobroDto.estadoFormaPagoCobro
                tbaFormaPagoCobroRepository.save(tbaFormaPagoCobro)
            }

    fun getPaymentCollectionMethod(id: String): Mono<FormaPagoCobro> =
        tbaFormaPagoCobroRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PAYMENT_COLLECTION_METHOD_NOT_FOUND + id)))

    fun getAllPaymentCollectionMethod(): Flux<FormaPagoCobro> =
        tbaFormaPagoCobroRepository.findByEstadoFormaPagoCobro(true)

    fun getRequestBySearchParams(
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<FormaPagoCobro>> {
        val formaPagoCobroFlux = tbaFormaPagoCobroRepository.getBySearchParams(sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaFormaPagoCobroRepository.getCountBySearchParams()
        return total.flatMap { totalCount ->
            formaPagoCobroFlux.collectList().map { formaPagoCobros -> Paginacion(formaPagoCobros, page, limit, totalCount.toInt()) }
        }
    }
}
