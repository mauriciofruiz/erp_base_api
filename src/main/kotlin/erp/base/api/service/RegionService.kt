package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.RegionDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Region
import erp.base.api.repository.RegionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RegionService(private val tbaRegionRepository: RegionRepository) {

    fun createRegion(regionDto: RegionDto): Mono<Region> =
        tbaRegionRepository.save(Region(null, regionDto.nombreRegion))

    fun updateRegion(regionId: Int, regionDto: RegionDto): Mono<Region> =
        tbaRegionRepository.existsById(regionId).flatMap { exists ->
            if (exists) tbaRegionRepository.save(Region(regionId, regionDto.nombreRegion))
            else Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REGION_NOT_FOUND + regionId))
        }

    fun getRegionById(regionId: Int): Mono<Region> =
        tbaRegionRepository.findById(regionId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REGION_NOT_FOUND + regionId)))

    fun getRegionByName(regionName: String?): Flux<Region> =
        if (regionName == null) tbaRegionRepository.findAll()
        else tbaRegionRepository.findByNombreRegionContaining(regionName)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.REGION_NAME_NOT_FOUND + regionName)))
}
