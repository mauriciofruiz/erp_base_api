package erp.base.api.service

import erp.base.api.constants.ErrorMessage
import erp.base.api.dto.BitacoraDto
import erp.base.api.enums.CategoriaClienteEnum
import erp.base.api.enums.EstadoCivilEnum
import erp.base.api.enums.GeneroEnum
import erp.base.api.enums.RetencionFuenteEnum
import erp.base.api.enums.RetencionIVAEnum
import erp.base.api.enums.TipoConstribuyenteEnum
import erp.base.api.enums.TipoFiscalEnum
import erp.base.api.exception.CustomException
import erp.base.api.models.Bitacora
import erp.base.api.models.Cliente
import erp.base.api.models.CreditoCliente
import erp.base.api.repository.BitacoraRepository
import erp.base.api.repository.ClienteRepository
import erp.base.api.repository.CreditoClienteRepository
import erp.base.api.repository.custom.BitacoraCustom
import erp.base.api.utils.Paginacion
import erp.base.api.utils.Utils
import erp.base.api.utils.UtilsBitacora
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class BitacoraService(
    private val bitacoraRepository: BitacoraRepository,
    private val bitacoraCustom: BitacoraCustom,
    private val tbaClienteRepository: ClienteRepository,
    private val tbaCreditoClienteRepository: CreditoClienteRepository,
    private val excepcionesClienteDescuentoService: ExcepcionesClienteDescuentoService
) {

    fun saveTransactionLog(bitacora: Bitacora, ip: String, hostName: String): Mono<Bitacora> {
        bitacora.fecha = Utils.currentZonedDateTimeEcuador()
        bitacora.ipDispositivo = ip
        bitacora.nombreDispositivo = hostName
        return bitacoraRepository.save(bitacora)
    }

    fun findAllByIdAndTable(id: Int, table: String): Flux<BitacoraDto> =
        bitacoraRepository.findAllByIdAndTable(id, table)

    fun getTransactionLogByClient(
        clientId: Int,
        page: Int,
        limit: Int,
        sortDirection: String?,
        sortField: String?
    ): Mono<Paginacion<BitacoraDto>> {
        val client = tbaClienteRepository.findById(clientId)
            .switchIfEmpty(Mono.error(CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND)))
        val creditClient = tbaCreditoClienteRepository.findByIdCliente(clientId)
            .switchIfEmpty(Mono.just(CreditoCliente()))
        val exceptions = excepcionesClienteDescuentoService.getExceptionsByClientId(clientId.toLong()).collectList()
        return Mono.zip(client, creditClient, exceptions).flatMap { tuple ->
            val exceptionsIds = tuple.t3.mapNotNull { it.idExcepcion }
            val ids = mutableListOf<Int>(
                tuple.t1.idCliente!!,
                tuple.t1.idPersona!!,
                tuple.t2.idCreditoCliente ?: 0
            )
            ids.addAll(exceptionsIds)
            val bitacoraDtoFlux = bitacoraCustom.getTransactionLogByClient(ids, sortField, sortDirection, limit, (page - 1) * limit)
                .map(this::transformBitacora)
            val total = bitacoraCustom.getCountTransactionLogByClient(ids)
            total.flatMap { totalCount ->
                bitacoraDtoFlux.collectList().map { bitacora -> Paginacion(bitacora, page, limit, totalCount.toInt()) }
            }
        }
    }

    private fun transformBitacora(bitacora: BitacoraDto): BitacoraDto {
        val campoBitacora = bitacora.campo
        when (campoBitacora) {
            "id_genero" -> {
                bitacora.oldValue = bitacora.oldValue?.let { GeneroEnum.getDescriptionById(it.toInt()) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { GeneroEnum.getDescriptionById(it.toInt()) } ?: ""
            }
            "id_estadocivil" -> {
                bitacora.oldValue = bitacora.oldValue?.let { EstadoCivilEnum.getDescriptionById(it.toInt()) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { EstadoCivilEnum.getDescriptionById(it.toInt()) } ?: ""
            }
            "id_tipoidfiscal" -> {
                bitacora.oldValue = bitacora.oldValue?.let { TipoFiscalEnum.getTipoIdentificacionById(it) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { TipoFiscalEnum.getTipoIdentificacionById(it) } ?: ""
            }
            "faccontado_cliente" -> {
                bitacora.oldValue = when (bitacora.oldValue) { "true" -> "Sí"; "false" -> "No"; else -> "" }
                bitacora.newValue = when (bitacora.newValue) { "true" -> "Sí"; "false" -> "No"; else -> "" }
            }
            "id_categoriacliente" -> {
                bitacora.oldValue = bitacora.oldValue?.let { CategoriaClienteEnum.getDescriptionById(it.toInt()) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { CategoriaClienteEnum.getDescriptionById(it.toInt()) } ?: ""
            }
            "estado_cliente" -> {
                bitacora.oldValue = when (bitacora.oldValue) { "true" -> "Activo"; "false" -> "Inactivo"; else -> "" }
                bitacora.newValue = when (bitacora.newValue) { "true" -> "Activo"; "false" -> "Inactivo"; else -> "" }
            }
            "id_tipocontribuyente" -> {
                bitacora.oldValue = bitacora.oldValue?.let { TipoConstribuyenteEnum.getNameById(it) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { TipoConstribuyenteEnum.getNameById(it) } ?: ""
            }
            "id_retencionfuente" -> {
                bitacora.oldValue = bitacora.oldValue?.let { RetencionFuenteEnum.getDescriptionById(it) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { RetencionFuenteEnum.getDescriptionById(it) } ?: ""
            }
            "id_retencioniva" -> {
                bitacora.oldValue = bitacora.oldValue?.let { RetencionIVAEnum.getDescriptionById(it.toInt()) } ?: ""
                bitacora.newValue = bitacora.newValue?.let { RetencionIVAEnum.getDescriptionById(it.toInt()) } ?: ""
            }
            else -> {}
        }

        bitacora.campo = UtilsBitacora.CAMPO_BITACORA_TRANSLATIONS[bitacora.campo] ?: bitacora.campo
        return bitacora
    }
}
