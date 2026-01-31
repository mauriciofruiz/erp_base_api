package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.PaisDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Pais
import erp.base.api.repository.PaisRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PaisService(private val tbaPaisRepository: PaisRepository) {

    fun createCountry(countryDto: PaisDto): Mono<Pais> =
        tbaPaisRepository.save(Pais(null, countryDto.codigoPais, countryDto.nombrePais))

    fun updateCountry(countryId: Int, countryDto: PaisDto): Mono<Pais> =
        tbaPaisRepository.existsById(countryId).flatMap { exists ->
            if (exists) tbaPaisRepository.save(Pais(countryId, countryDto.codigoPais, countryDto.nombrePais))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.COUNTRY_NOT_FOUND + countryId))
        }

    fun getCountryById(id: Int): Mono<Pais> =
        tbaPaisRepository.findById(id)

    fun getAllCountries(): Flux<Pais> =
        tbaPaisRepository.findAll()
}
