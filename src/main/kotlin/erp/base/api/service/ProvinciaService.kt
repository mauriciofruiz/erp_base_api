package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.ProvinciaDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Provincia
import erp.base.api.repository.PaisRepository
import erp.base.api.repository.ProvinciaRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProvinciaService(
    private val tbaProvinciaRepository: ProvinciaRepository,
    private val tbaPaisRepository: PaisRepository
) {

    fun createProvince(provinceDto: ProvinciaDto): Mono<Provincia> {
        val provinceExists = tbaProvinciaRepository.existsById(provinceDto.idProvincia!!)
        val countryExists = tbaPaisRepository.existsById(provinceDto.idPais!!)
        return provinceExists.flatMap { existsProvinceId ->
            if (existsProvinceId) {
                Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.PROVINCE_ALREADY_EXISTS + provinceDto.idProvincia))
            } else {
                countryExists.flatMap { exists ->
                    if (exists) {
                        tbaProvinciaRepository.insert(
                            provinceDto.idProvincia!!,
                            provinceDto.idPais!!,
                            provinceDto.nombreProvincia!!,
                            provinceDto.codRegion!!
                        ).then(tbaProvinciaRepository.findById(provinceDto.idProvincia!!))
                    } else {
                        Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.COUNTRY_NOT_FOUND + provinceDto.idPais))
                    }
                }
            }
        }
    }

    fun updateProvince(provinceId: String, tbaProvinciaDto: ProvinciaDto): Mono<Provincia> {
        val provinceExists = tbaProvinciaRepository.existsById(provinceId)
        val countryExists = tbaPaisRepository.existsById(tbaProvinciaDto.idPais!!)
        return provinceExists.flatMap { exists ->
            if (exists) {
                countryExists.flatMap { existsC ->
                    if (existsC) {
                        tbaProvinciaRepository.save(
                            Provincia(
                                idProvincia = provinceId,
                                idPais = tbaProvinciaDto.idPais,
                                nombreProvincia = tbaProvinciaDto.nombreProvincia,
                                codRegion = tbaProvinciaDto.codRegion
                            )
                        )
                    } else {
                        Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.COUNTRY_NOT_FOUND + tbaProvinciaDto.idPais))
                    }
                }
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + provinceId))
            }
        }
    }

    fun getProvinceById(provinceId: String): Mono<Provincia> =
        tbaProvinciaRepository.findById(provinceId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.PROVINCE_NOT_FOUND + provinceId)))

    fun getProvinceByParams(countryId: Int?, provinceName: String?, regionCode: String?): Flux<Provincia> {
        val isSearchByCountryId = countryId != null && provinceName == null && regionCode == null
        val isSearchByProvinceName = countryId == null && provinceName != null && regionCode == null
        val isSearchByCodRegion = countryId == null && provinceName == null && regionCode != null
        val isSearchByCountryIdAndProvinceName = countryId != null && provinceName != null && regionCode == null
        val isSearchByCountryIdAndCodRegion = countryId != null && provinceName == null && regionCode != null
        val isSearchByProvinceNameAndCodRegion = countryId == null && provinceName != null && regionCode != null
        val isSearchByCountryIdAndProvinceNameAndCodRegion = countryId != null && provinceName != null && regionCode != null
        return when {
            isSearchByCountryId -> tbaProvinciaRepository.findByIdPais(countryId!!)
            isSearchByProvinceName -> tbaProvinciaRepository.findByNombreProvinciaContaining(provinceName!!)
            isSearchByCodRegion -> tbaProvinciaRepository.findByCodRegionContaining(regionCode!!)
            isSearchByCountryIdAndProvinceName -> tbaProvinciaRepository.findByIdPaisAndNombreProvinciaContaining(countryId!!, provinceName!!)
            isSearchByCountryIdAndCodRegion -> tbaProvinciaRepository.findByIdPaisAndCodRegionContaining(countryId!!, regionCode!!)
            isSearchByProvinceNameAndCodRegion -> tbaProvinciaRepository.findByNombreProvinciaContainingAndCodRegionContaining(provinceName!!, regionCode!!)
            isSearchByCountryIdAndProvinceNameAndCodRegion -> tbaProvinciaRepository.findByIdPaisAndNombreProvinciaContainingAndCodRegionContaining(countryId!!, provinceName!!, regionCode!!)
            else -> tbaProvinciaRepository.findAll()
        }
    }
}
