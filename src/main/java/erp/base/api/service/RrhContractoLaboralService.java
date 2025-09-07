package erp.base.api.service;

import erp.base.api.dto.RrhhContratosDto;
import erp.base.api.enums.ContratHourEnum;
import erp.base.api.models.rrh.RrhContratoLaboral;
import erp.base.api.repository.rrh.RrhContratoLaboralRepository;
import erp.base.api.repository.rrh.RrhTipoContratoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RrhContractoLaboralService {

    private final RrhContratoLaboralRepository rrhContratoLaboralRepository;
    private final RrhTipoContratoRepository rrhTipoContratoRepository;

    private RrhContratoLaboral rrhContratoLaboral;
    private static final List<String> TYPES_CONTRACT_TO_ADD = Arrays.asList(
            "INDEFINIDO - TIEMPO DE PRUEBA",
            "INDEFINIDO - JUVENIL",
            "ESPECIAL EMERGENTE",
            "CT PROYECTO",
            "CONTRATO PRODUCTIVO"
    );

    public Flux<RrhContratoLaboral> createWorkContract(List<RrhhContratosDto> workContractDto, Integer idEmployee, LocalDateTime currentDate) {
       return Flux.fromIterable(workContractDto).flatMap(workContractDto1 -> {
            Mono<LocalDate> testEndDate = testEndDate(workContractDto1.getIdTipocontrato(), workContractDto1.getFechainicioContrato());

            return testEndDate.flatMap(testEndDate1 -> {
                rrhContratoLaboral = RrhContratoLaboral.builder()
                        .idEmpleado(idEmployee)
                        .idTipoContrato(workContractDto1.getIdTipocontrato())
                        .idModalidadContrato(workContractDto1.getIdModalidadcontrato())
                        .fechaInicioContrato(workContractDto1.getFechainicioContrato() != null ? workContractDto1.getFechainicioContrato() : LocalDate.from(currentDate))
                        .finPruebaContrato(testEndDate1)
                        .estadoContrato(true)
                        .numeroHorasContrato(workContractDto1.getNrohorasContrato())
                        .build();
                return rrhContratoLaboralRepository.save(rrhContratoLaboral);
            });
        });
    }

    public Mono<LocalDate> testEndDate(Integer contractTypeId, LocalDate contractStartDate) {
        return rrhTipoContratoRepository.findById(contractTypeId)
                .flatMap(contractType -> {
                    if (contractType != null && TYPES_CONTRACT_TO_ADD.contains(contractType.getNombreTipoContrato())) {
                        return Mono.just(contractStartDate.plusMonths(3));
                    } else {
                        return Mono.empty();
                    }
                });
    }


    public Flux<RrhContratoLaboral> updateWorkContract(List<RrhhContratosDto> contractListDto, Integer employeeId) {
        // Convertimos la lista de DTOs en un Flux
        return Flux.fromIterable(contractListDto).flatMap(contractDto ->
                        // Buscamos el contrato que coincide con el ID del DTO
                        rrhContratoLaboralRepository.findById(contractDto.getIdContrato()).filter(contrato -> contrato.getIdEmpleado().equals(employeeId))  // Aseguramos que el contrato pertenece al empleado correcto
                                .map(contract -> {
                                    // Actualizamos el contrato con la información del DTO
                                    contract.setIdTipoContrato(contractDto.getIdTipocontrato());
                                    contract.setIdModalidadContrato(contractDto.getIdModalidadcontrato());
                                    contract.setEstadoContrato(contractDto.getEstadoContrato());

                                    if (contract.getIdModalidadContrato() == 2) {
                                        contract.setNumeroHorasContrato(contractDto.getNrohorasContrato());
                                    } else {
                                        contract.setNumeroHorasContrato(ContratHourEnum.FULL_TIME.getHours());
                                    }

                                    return contract;
                                }))
                // Una vez que todos los contratos han sido procesados, los guardamos
                .flatMap(rrhContratoLaboralRepository::save);
    }
}
