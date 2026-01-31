package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.ExcepcionesClienteDescDto
import erp.base.api.enums.TipoExcepcionClienteEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.ExcepcionesClienteDescuento
import erp.base.api.models.Grupo
import erp.base.api.models.Producto
import erp.base.api.models.SubGrupo
import erp.base.api.repository.ExcepcionesClienteDescuentoRepository
import erp.base.api.repository.GrupoRepository
import erp.base.api.repository.ProductoRepository
import erp.base.api.repository.SubgrupoRepository
import erp.base.api.utils.Paginacion
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ExcepcionesClienteDescuentoService(
    private val excepcionesClienteDescuentoRepository: ExcepcionesClienteDescuentoRepository,
    private val productoRepository: ProductoRepository,
    private val subgrupoRepository: SubgrupoRepository,
    private val grupoRepository: GrupoRepository
) {

    fun getAllDtoByIdCliente(idCliente: Long): Flux<ExcepcionesClienteDescDto> =
        excepcionesClienteDescuentoRepository.findAllByIdCliente(idCliente)
            .flatMapSequential(this::mapToDto)

    private fun mapToDto(excepcionesClienteDescuento: ExcepcionesClienteDescuento): Mono<ExcepcionesClienteDescDto> {
        val valorParametroMono = when {
            excepcionesClienteDescuento.idProducto != null -> productoRepository.findById(excepcionesClienteDescuento.idProducto!!)
                .map { (it.codProducto ?: "").plus(" - ").plus(it.nombreCortoProducto ?: "") }
                .switchIfEmpty(Mono.just("Producto no encontrado"))
            excepcionesClienteDescuento.idSubgrupo != null -> subgrupoRepository.findById(excepcionesClienteDescuento.idSubgrupo!!)
                .map(SubGrupo::nombreSubGrupo)
                .switchIfEmpty(Mono.just("Subgrupo no encontrado"))
            else -> grupoRepository.findById(excepcionesClienteDescuento.idGrupo!!)
                .map(Grupo::nombreGrupo)
                .switchIfEmpty(Mono.just("Grupo no encontrado"))
        }
        val idTipoExcepcion = when {
            excepcionesClienteDescuento.idProducto != null -> TipoExcepcionClienteEnum.PRODUCTO.identification
            excepcionesClienteDescuento.idSubgrupo != null -> TipoExcepcionClienteEnum.SUBGRUPO.identification
            else -> TipoExcepcionClienteEnum.GRUPO.identification
        }
        val idParametro = excepcionesClienteDescuento.idProducto
            ?: excepcionesClienteDescuento.idSubgrupo
            ?: excepcionesClienteDescuento.idGrupo!!
        return valorParametroMono.map { valorParametro ->
            ExcepcionesClienteDescDto().apply {
                idExcepcion = excepcionesClienteDescuento.idExcepcion
                idCliente = excepcionesClienteDescuento.idCliente
                this.idTipoExcepcion = idTipoExcepcion
                this.idParametro = idParametro
                this.valorParametro = valorParametro
                porcionDescuento = excepcionesClienteDescuento.porcionDescuento
                fechaInicio = excepcionesClienteDescuento.fechaInicio
                fechaFin = excepcionesClienteDescuento.fechaFin
                estado = excepcionesClienteDescuento.estado
            }
        }
    }

    fun saveUpdateAllDto(excepcionesClienteDescDtoFlux: Flux<ExcepcionesClienteDescDto>, idCliente: Long): Flux<ExcepcionesClienteDescDto> =
        excepcionesClienteDescDtoFlux
            .map { mapToEntity(it, idCliente) }
            .flatMapSequential(excepcionesClienteDescuentoRepository::save)
            .flatMapSequential(this::mapToDto)

    fun getExceptionsByClientId(clientId: Long): Flux<ExcepcionesClienteDescuento> =
        excepcionesClienteDescuentoRepository.findAllByIdCliente(clientId)

    fun deleteRemovedDetails(exceptionsListBDD: List<ExcepcionesClienteDescuento>, excepcionesClienteDescDtoList: List<ExcepcionesClienteDescDto>): Mono<Boolean> {
        val exceptionsListRequest = excepcionesClienteDescDtoList.map { mapToEntity(it, it.idCliente!!) }
        val exceptionListToDelete = exceptionsListBDD.filter { it !in exceptionsListRequest }
        return deleteAllExceptions(exceptionListToDelete).then(Mono.just(true))
    }

    fun deleteAllExceptions(detalleList: List<ExcepcionesClienteDescuento>): Mono<Void> =
        excepcionesClienteDescuentoRepository.deleteAll(detalleList)

    private fun mapToEntity(excepcionesClienteDescDto: ExcepcionesClienteDescDto, idCliente: Long): ExcepcionesClienteDescuento {
        val (idProducto, idSubgrupo, idGrupo) = when (excepcionesClienteDescDto.idTipoExcepcion) {
            TipoExcepcionClienteEnum.PRODUCTO.identification -> Triple(excepcionesClienteDescDto.idParametro, null, null)
            TipoExcepcionClienteEnum.SUBGRUPO.identification -> Triple(null, excepcionesClienteDescDto.idParametro, null)
            else -> Triple(null, null, excepcionesClienteDescDto.idParametro)
        }
        return ExcepcionesClienteDescuento(
            idExcepcion = excepcionesClienteDescDto.idExcepcion,
            idCliente = idCliente,
            idProducto = idProducto,
            idSubgrupo = idSubgrupo,
            idGrupo = idGrupo,
            porcionDescuento = excepcionesClienteDescDto.porcionDescuento,
            fechaInicio = excepcionesClienteDescDto.fechaInicio,
            fechaFin = excepcionesClienteDescDto.fechaFin,
            estado = excepcionesClienteDescDto.estado,
            idUsuarioCreacion = null,
            idUsuarioModificacion = null,
            fechaCreacion = null,
            fechaModificacion = null
        )
    }

    @Transactional(readOnly = true)
    fun getRequestsBySearchParams(clientId: Int, page: Int, limit: Int, sortDirection: String?, sortField: String?): Mono<Paginacion<ExcepcionesClienteDescDto>> {
        val pageable: Pageable = try {
            val sort = if (sortDirection != null && sortField != null) Sort.by(Sort.Direction.fromString(sortDirection), sortField) else Sort.unsorted()
            PageRequest.of(page - 1, limit, sort)
        } catch (e: Exception) {
            return Mono.error(CustomException(HttpStatus.BAD_REQUEST, e.cause?.message ?: e.message ?: ""))
        }
        val excepcionesClienteDescDtoFlux = excepcionesClienteDescuentoRepository.findByIdClienteAndEstado(clientId.toLong(), true, pageable)
            .flatMapSequential(this::mapToDto)
        val totalRegisters = excepcionesClienteDescuentoRepository.countByIdClienteAndEstado(clientId.toLong(), true)
        return excepcionesClienteDescDtoFlux.collectList().zipWith(totalRegisters)
            .map { tuple -> Paginacion(tuple.t1, page, limit, tuple.t2.toInt()) }
    }

    @Transactional
    fun delete(id: Int): Mono<Void> =
        excepcionesClienteDescuentoRepository.findById(id)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.EXCEPTION_NOT_FOUND + id)))
            .flatMap(excepcionesClienteDescuentoRepository::delete)
            .then()
}
