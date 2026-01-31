package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.GeneroDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Genero
import erp.base.api.repository.GeneroRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class GeneroService(private val tbaGeneroRepository: GeneroRepository) {

    fun createGender(genderDto: GeneroDto): Mono<Genero> =
        tbaGeneroRepository.save(Genero(null, genderDto.nombreGenero, genderDto.codigoGenero))

    fun updateGender(genderId: Int, genderDto: GeneroDto): Mono<Genero> =
        tbaGeneroRepository.existsById(genderId).flatMap { exists ->
            if (exists) tbaGeneroRepository.save(Genero(genderId, genderDto.nombreGenero, genderDto.codigoGenero))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.GENDER_NOT_FOUND + genderId))
        }

    fun getGenderById(genderId: Int): Mono<Genero> =
        tbaGeneroRepository.findById(genderId)

    fun getAllGenders(): Flux<Genero> =
        tbaGeneroRepository.findAll()
}
