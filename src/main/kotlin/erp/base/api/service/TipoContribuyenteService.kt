package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.TipoContribuyenteDto
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoContribuyente
import erp.base.api.repository.TipoContribuyenteRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class TipoContribuyenteService(private val trbTipoContribuyenteRepository: TipoContribuyenteRepository) {

    fun createContributorType(trbTipoContribuyenteDto: TipoContribuyenteDto): Mono<TipoContribuyente> =
        trbTipoContribuyenteRepository.existsById(trbTipoContribuyenteDto.idTipoContribuyente!!).flatMap { exists ->
            if (exists) Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.EXISTING_CONTRIBUTOR_TYPE + trbTipoContribuyenteDto.idTipoContribuyente))
            else trbTipoContribuyenteRepository.createContributorType(
                trbTipoContribuyenteDto.idTipoContribuyente!!,
                trbTipoContribuyenteDto.nombreTipoContribuyente!!,
                trbTipoContribuyenteDto.codigoTipoContribuyente!!
            ).then(trbTipoContribuyenteRepository.findById(trbTipoContribuyenteDto.idTipoContribuyente!!))
        }

    fun updateContributorType(contributorTypeId: String, trbTipoContribuyenteDto: TipoContribuyenteDto): Mono<TipoContribuyente> =
        trbTipoContribuyenteRepository.existsById(contributorTypeId).flatMap { exists ->
            if (exists) trbTipoContribuyenteRepository.save(TipoContribuyente(contributorTypeId, trbTipoContribuyenteDto.nombreTipoContribuyente, trbTipoContribuyenteDto.codigoTipoContribuyente))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CONTRIBUTOR_TYPE_NOT_FOUND + contributorTypeId))
        }

    fun getContributorTypeById(contributorTypeId: String): Mono<TipoContribuyente> =
        trbTipoContribuyenteRepository.findById(contributorTypeId)

    fun getAllContributorTypes(): Flux<TipoContribuyente> =
        trbTipoContribuyenteRepository.findAll()
}
