package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.ParroquiaDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Parroquia
import erp.base.api.repository.CiudadRepository
import erp.base.api.repository.ParroquiaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ParroquiaService(
    private val tbaParroquiaRepository: ParroquiaRepository,
    private val tbaCiudadRepository: CiudadRepository
) {

    fun createParish(parishDto: ParroquiaDto): Mono<Parroquia> {
        val existsCityById = tbaCiudadRepository.existsById(parishDto.idCiudad!!)
        return existsCityById.flatMap { exists ->
            if (exists) {
                tbaParroquiaRepository.save(
                    Parroquia(
                        idParroquia = null,
                        idCiudad = parishDto.idCiudad,
                        nombreParroquia = parishDto.nombreParroquia,
                        codParroquia = parishDto.codParroquia
                    )
                )
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + parishDto.idCiudad))
            }
        }
    }

    fun updateParish(parishId: Int, parishDto: ParroquiaDto): Mono<Parroquia> {
        val isCityExistsById = tbaCiudadRepository.existsById(parishDto.idCiudad!!)
        val isParishExistsById = tbaParroquiaRepository.existsById(parishId)
        return isCityExistsById.flatMap { cityExists ->
            if (cityExists) {
                isParishExistsById.flatMap { existsParish ->
                    if (existsParish) {
                        tbaParroquiaRepository.save(
                            Parroquia(
                                idParroquia = parishId,
                                idCiudad = parishDto.idCiudad,
                                nombreParroquia = parishDto.nombreParroquia,
                                codParroquia = parishDto.codParroquia
                            )
                        )
                    } else {
                        Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PARISH_NOT_FOUND + parishId))
                    }
                }
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + parishDto.idCiudad))
            }
        }
    }

    fun getParishById(parishId: Int): Mono<Parroquia> =
        tbaParroquiaRepository.findById(parishId)

    fun getAllParishes(): Flux<Parroquia> =
        tbaParroquiaRepository.findAll()

    fun getParish(parishName: String?): Flux<ParroquiaDto> =
        tbaParroquiaRepository.findParishByName(parishName!!)
            .switchIfEmpty(Flux.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PARISH_NOT_FOUND)))

    fun getParishByCityId(cityId: Int): Flux<Parroquia> =
        tbaParroquiaRepository.findByIdCiudad(cityId)
}
