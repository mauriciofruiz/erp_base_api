package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.CiudadDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Ciudad
import erp.base.api.repository.CiudadRepository
import erp.base.api.repository.ProvinciaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CiudadService(
    private val tbaCiudadRepository: CiudadRepository,
    private val tbaProvinciaRepository: ProvinciaRepository
) {

    fun createCity(cityDto: CiudadDto): Mono<Ciudad> {
        val alreadyExistsProvince = tbaProvinciaRepository.existsById(cityDto.idProvincia!!)
        return alreadyExistsProvince.flatMap { exists ->
            if (exists) {
                tbaCiudadRepository.save(
                    Ciudad(
                        idCiudad = null,
                        idProvincia = cityDto.idProvincia,
                        nombreCiudad = cityDto.nombreCiudad,
                        codigoCiudad = cityDto.codigoCiudad
                    )
                )
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + cityDto.idProvincia))
            }
        }
    }

    fun updateCity(cityId: Int, cityDto: CiudadDto): Mono<Ciudad> {
        val alreadyExistsProvince = tbaProvinciaRepository.existsById(cityDto.idProvincia!!)
        val alreadyExistsCity = tbaCiudadRepository.existsById(cityId)
        return alreadyExistsCity.flatMap { existsCity ->
            if (existsCity) {
                alreadyExistsProvince.flatMap { exists ->
                    if (exists) {
                        tbaCiudadRepository.save(
                            Ciudad(
                                idCiudad = cityId,
                                idProvincia = cityDto.idProvincia,
                                nombreCiudad = cityDto.nombreCiudad,
                                codigoCiudad = cityDto.codigoCiudad
                            )
                        )
                    } else {
                        Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + cityDto.idProvincia))
                    }
                }
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + cityId))
            }
        }
    }

    fun getCityById(cityId: Int): Mono<Ciudad> =
        tbaCiudadRepository.findById(cityId).switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND + cityId)))

    fun getCityByParams(provinceId: String?, cityName: String?, cityCode: String?): Flux<Ciudad> {
        val isSearchByProvinceId = provinceId != null && cityName == null && cityCode == null
        val isSearchByCityName = provinceId == null && cityName != null && cityCode == null
        val isSearchByCityCode = provinceId == null && cityName == null && cityCode != null
        return when {
            isSearchByProvinceId -> tbaCiudadRepository.findByIdProvincia(provinceId!!)
            isSearchByCityName -> tbaCiudadRepository.findByNombreCiudadContaining(cityName!!)
            isSearchByCityCode -> tbaCiudadRepository.findByCodigoCiudad(cityCode!!)
            else -> tbaCiudadRepository.findAll()
        }
    }

    fun getCityByIdParish(parishId: Int): Mono<CiudadDto> =
        tbaCiudadRepository.getCityByIdParish(parishId).switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CITY_NOT_FOUND)))
}
