package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.controller.InventarioController
import erp.base.api.dto.BodegaDto
import erp.base.api.dto.SucursalDto
import erp.base.api.exception.CustomException
import erp.base.api.models.Sucursal
import erp.base.api.repository.SucursalRepository
import erp.base.api.repository.custom.SucursalCustom
import erp.base.api.utils.Paginacion
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.function.Consumer

@Service
class SucursalService(
    private val tbaSucursalRepository: SucursalRepository,
    private val tbaSucursalCustom: SucursalCustom,
    private val inventarioController: InventarioController
) {

    fun createBranch(branchDto: SucursalDto, httpHeaders: Consumer<HttpHeaders>): Mono<Sucursal> =
        tbaSucursalRepository.findTopByOrderByIdSucursalDesc()
            .defaultIfEmpty(Sucursal())
            .flatMap { lastSucursal ->
                val lastId = lastSucursal.idSucursal ?: 0
                val nemonic = createNemonic(lastId + 1)
                val branchCode = createBranchCode(lastId + 1)
                val newSucursal = Sucursal(
                    idSucursal = null,
                    idCiudad = branchDto.idCiudad,
                    idCategoriaSucursal = branchDto.idCategoriaSucursal,
                    idRegion = branchDto.idRegion,
                    nombreSucursal = branchDto.nombreSucursal,
                    nemonicoSucursal = nemonic,
                    direccionSucursal = branchDto.direccionSucursal,
                    telefonoSucursal = branchDto.telefonoSucursal,
                    telefono2Sucursal = branchDto.telefono2Sucursal,
                    celularSucursal = branchDto.celularSucursal,
                    correoSucursal = branchDto.correoSucursal,
                    correo2Sucursal = branchDto.correo2Sucursal,
                    responsableSucursal = branchDto.responsableSucursal,
                    esOutletSucursal = branchDto.esOutletSucursal ?: false,
                    estadoSucursal = branchDto.estadoSucursal ?: true,
                    impresionTermicaSucursal = branchDto.impresionTermicaSucursal ?: false,
                    codigoSucursal = branchCode
                )
                tbaSucursalRepository.save(newSucursal)
                    .flatMap { sucursal ->
                        val bodegaDtoList = branchDto.bodegaList?.map { bodega ->
                            bodega.apply { sucursalId = sucursal.idSucursal }
                        } ?: emptyList()
                        inventarioController.createWareHouse(bodegaDtoList, httpHeaders)
                            .then(Mono.just(sucursal))
                            .onErrorResume { e -> Mono.error(CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "")) }
                    }
            }

    fun createNemonic(idSucursal: Int): String = "S%02d".format(idSucursal)

    companion object {
        @JvmStatic
        fun createBranchCode(idSucursal: Int): String = "%03d".format(idSucursal)
    }

    fun updateBranch(branchId: Int, branchDto: SucursalDto, httpHeaders: Consumer<HttpHeaders>): Mono<Sucursal> =
        tbaSucursalRepository.findById(branchId)
            .flatMap { existingSucursal ->
                existingSucursal.idCiudad = branchDto.idCiudad
                existingSucursal.idCategoriaSucursal = branchDto.idCategoriaSucursal
                existingSucursal.idRegion = branchDto.idRegion
                existingSucursal.nombreSucursal = branchDto.nombreSucursal
                existingSucursal.nemonicoSucursal = branchDto.nemonicoSucursal
                existingSucursal.direccionSucursal = branchDto.direccionSucursal
                existingSucursal.telefonoSucursal = branchDto.telefonoSucursal
                existingSucursal.telefono2Sucursal = branchDto.telefono2Sucursal
                existingSucursal.celularSucursal = branchDto.celularSucursal
                existingSucursal.correoSucursal = branchDto.correoSucursal
                existingSucursal.correo2Sucursal = branchDto.correo2Sucursal
                existingSucursal.responsableSucursal = branchDto.responsableSucursal
                existingSucursal.esOutletSucursal = branchDto.esOutletSucursal
                existingSucursal.estadoSucursal = branchDto.estadoSucursal
                existingSucursal.impresionTermicaSucursal = branchDto.impresionTermicaSucursal
                existingSucursal.codigoSucursal = branchDto.codigoSucursal
                tbaSucursalRepository.save(existingSucursal)
                    .flatMap { updatedSucursal ->
                        inventarioController.updateWareHouse(branchDto.bodegaList ?: emptyList(), httpHeaders)
                            .then(Mono.just(updatedSucursal))
                            .onErrorResume { e -> Mono.error(CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "")) }
                    }
            }
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_NOT_FOUND + branchId)))

    fun getBranchById(branchId: Int): Mono<Sucursal> =
        tbaSucursalRepository.findById(branchId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_NOT_FOUND + branchId)))

    fun getBranchesByParams(
        cityId: Int?,
        regionId: Int?,
        categoryBranchId: String?,
        name: String?,
        mnemonic: String?,
        isOutlet: Boolean?,
        isActive: Boolean?,
        isProduction: Boolean?
    ): Flux<Sucursal> {
        val isSearchByCity = cityId != null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && isOutlet != true && isActive != true && isProduction != true
        val isSearchByRegion = cityId == null && regionId != null && categoryBranchId == null && name == null && mnemonic == null && isOutlet != true && isActive != true && isProduction != true
        val isSearchByCategoryBranch = cityId == null && regionId == null && categoryBranchId != null && name == null && mnemonic == null && isOutlet != true && isActive != true && isProduction != true
        val isSearchByName = cityId == null && regionId == null && categoryBranchId == null && name != null && mnemonic == null && isOutlet != true && isActive != true && isProduction != true
        val isSearchByMnemonic = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic != null && isOutlet != true && isActive != true && isProduction != true
        val isSearchByIsOutlet = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && isOutlet == true && isActive != true && isProduction != true
        val isSearchByIsActive = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && isOutlet != true && isActive == true && isProduction != true
        val isSearchByProduction = cityId == null && regionId == null && categoryBranchId == null && name == null && mnemonic == null && isOutlet != true && isActive != true && isProduction == true
        return when {
            isSearchByCity -> tbaSucursalRepository.findByIdCiudad(cityId!!)
            isSearchByRegion -> tbaSucursalRepository.findByIdRegion(regionId!!)
            isSearchByCategoryBranch -> tbaSucursalRepository.findByIdCategoriaSucursal(categoryBranchId!!)
            isSearchByName -> tbaSucursalRepository.findByNombreSucursalContaining(name!!)
            isSearchByMnemonic -> tbaSucursalRepository.findByNemonicoSucursal(mnemonic!!)
            isSearchByIsOutlet -> tbaSucursalRepository.findByEsOutletSucursal(isOutlet!!)
            isSearchByIsActive -> tbaSucursalRepository.findByEstadoSucursal(isActive!!)
            isSearchByProduction -> tbaSucursalCustom.getBranchProduction()
            else -> tbaSucursalRepository.findAll()
        }
    }

    fun getAllSucursales(): Flux<Sucursal> =
        tbaSucursalRepository.findByEstadoSucursal(true)

    fun getRequestBySearchParams(
        cityId: Int?,
        regionId: Int?,
        categoryBranchId: String?,
        name: String?,
        mnemonic: String?,
        isOutlet: Boolean?,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortfield: String?
    ): Mono<Paginacion<Sucursal>> {
        val sucursalFlux = tbaSucursalCustom.getBySearchParams(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet, sortfield, sortDirection, limit, (page - 1) * limit)
        val total = tbaSucursalCustom.getCountBySearchParams(cityId, regionId, categoryBranchId, name, mnemonic, isOutlet)
        return total.flatMap { totalCount ->
            sucursalFlux.collectList().map { sucursalList -> Paginacion(sucursalList, page, limit, totalCount.toInt()) }
        }
    }
}
