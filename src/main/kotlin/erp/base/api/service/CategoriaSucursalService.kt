package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.CategoriaSucursalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.CategoriaSucursal
import erp.base.api.repository.CategoriaSucursalRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CategoriaSucursalService(private val tbaCategoriaSucursalRepository: CategoriaSucursalRepository) {

    fun creatBranchCategory(tbaCategoriaSucursalDto: CategoriaSucursalDto): Mono<CategoriaSucursal> {
        val existsBranchCategory = tbaCategoriaSucursalRepository.existsById(tbaCategoriaSucursalDto.idCategoriaSucursal!!)
        return existsBranchCategory.flatMap { existsBranchCategoryId ->
            if (existsBranchCategoryId) {
                Mono.error(CustomException(HttpStatus.CONFLICT, ErrorMessage.BRANCH_CATEGORY_ID_EXISTS + tbaCategoriaSucursalDto.idCategoriaSucursal))
            } else {
                tbaCategoriaSucursalRepository.insert(
                    tbaCategoriaSucursalDto.idCategoriaSucursal!!,
                    tbaCategoriaSucursalDto.nombreCategoriaSucursal!!,
                    tbaCategoriaSucursalDto.descripcionCategoriaSucursal ?: ""
                ).then(tbaCategoriaSucursalRepository.findById(tbaCategoriaSucursalDto.idCategoriaSucursal!!))
            }
        }
    }

    fun updateBranchCategory(id: String, tbaCategoriaSucursalDto: CategoriaSucursalDto): Mono<CategoriaSucursal> {
        val existsBranchCategory = tbaCategoriaSucursalRepository.existsById(id)
        return existsBranchCategory.flatMap { branchCategory ->
            if (branchCategory) {
                tbaCategoriaSucursalRepository.save(
                    CategoriaSucursal(
                        idCategoriaSucursal = id,
                        nombreCategoriaSucursal = tbaCategoriaSucursalDto.nombreCategoriaSucursal,
                        descripcionCategoriaSucursal = tbaCategoriaSucursalDto.descripcionCategoriaSucursal
                    )
                )
            } else {
                Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_CATEGORY_NOT_FOUND + id))
            }
        }
    }

    fun getBranchCategoryId(id: String): Mono<CategoriaSucursal> =
        tbaCategoriaSucursalRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_CATEGORY_NOT_FOUND + id)))

    fun getBranchCategoryName(categoryName: String?): Flux<CategoriaSucursal> {
        val isSearchBranchName = categoryName == null
        return if (isSearchBranchName) {
            tbaCategoriaSucursalRepository.findAll()
        } else {
            tbaCategoriaSucursalRepository.findByNombreCategoriaSucursal(categoryName!!)
                .switchIfEmpty(Flux.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_CATEGORY_NOT_FOUND + categoryName)))
        }
    }
}
