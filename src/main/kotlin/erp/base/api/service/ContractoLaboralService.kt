package erp.base.api.service

import erp.base.api.dto.RrhhContratosDto
import erp.base.api.enums.ContratHourEnum
import erp.base.api.models.rrh.ContratoLaboral
import erp.base.api.repository.rrh.ContratoLaboralRepository
import erp.base.api.repository.rrh.TipoContratoRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class ContractoLaboralService(
    private val rrhContratoLaboralRepository: ContratoLaboralRepository,
    private val rrhTipoContratoRepository: TipoContratoRepository
) {

    companion object {
        private val TYPES_CONTRACT_TO_ADD = listOf(
            "INDEFINIDO - TIEMPO DE PRUEBA",
            "INDEFINIDO - JUVENIL",
            "ESPECIAL EMERGENTE",
            "CT PROYECTO",
            "CONTRATO PRODUCTIVO"
        )
    }

    fun createWorkContract(workContractDto: List<RrhhContratosDto>, idEmployee: Int, currentDate: LocalDateTime): Flux<ContratoLaboral> =
        Flux.fromIterable(workContractDto).flatMap { workContractDto1 ->
            val startDate = workContractDto1.fechainicioContrato ?: LocalDate.from(currentDate)
            testEndDate(workContractDto1.idTipocontrato, startDate).flatMap { testEndDate1 ->
                val rrhContratoLaboral = ContratoLaboral(
                    idContrato = null,
                    idTipoContrato = workContractDto1.idTipocontrato,
                    idModalidadContrato = workContractDto1.idModalidadcontrato,
                    idEmpleado = idEmployee,
                    fechaInicioContrato = startDate,
                    finPruebaContrato = testEndDate1,
                    fechaFinContrato = null,
                    archivoContrato = null,
                    estadoContrato = true,
                    numeroHorasContrato = workContractDto1.nrohorasContrato
                )
                rrhContratoLaboralRepository.save(rrhContratoLaboral)
            }
        }

    fun testEndDate(contractTypeId: Int?, contractStartDate: LocalDate?): Mono<LocalDate> =
        if (contractTypeId == null || contractStartDate == null) Mono.empty()
        else rrhTipoContratoRepository.findById(contractTypeId).flatMap { contractType ->
            if (contractType != null && TYPES_CONTRACT_TO_ADD.contains(contractType.nombreTipoContrato)) {
                Mono.just(contractStartDate.plusMonths(3))
            } else {
                Mono.empty()
            }
        }

    fun updateWorkContract(contractListDto: List<RrhhContratosDto>, employeeId: Int): Flux<ContratoLaboral> =
        Flux.fromIterable(contractListDto).flatMap { contractDto ->
            rrhContratoLaboralRepository.findById(contractDto.idContrato!!)
                .filter { it.idEmpleado == employeeId }
                .map { contract ->
                    contract.idTipoContrato = contractDto.idTipocontrato
                    contract.idModalidadContrato = contractDto.idModalidadcontrato
                    contract.estadoContrato = contractDto.estadoContrato
                    contract.numeroHorasContrato = if (contract.idModalidadContrato == 2) {
                        contractDto.nrohorasContrato
                    } else {
                        ContratHourEnum.FULL_TIME.hours
                    }
                    contract
                }
                .flatMap(rrhContratoLaboralRepository::save)
        }
}
