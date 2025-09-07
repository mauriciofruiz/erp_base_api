package erp.base.api.service;

import erp.base.api.dto.RrhContactoEmergenciaDto;
import erp.base.api.models.rrh.RrhContactoEmergencia;
import erp.base.api.repository.rrh.RrhContactoEmergenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RrhContactoEmergenciaService {

    private final RrhContactoEmergenciaRepository rrhContactoEmergenciaRepository;

    private RrhContactoEmergencia rrhContactoEmergencia;

    public Flux<RrhContactoEmergencia> createEmployeeEmergencyContact(List<RrhContactoEmergenciaDto> emergencyContactDto, Integer idEmployee) {
       List<RrhContactoEmergencia> emergencyContactList = new ArrayList<>();
        for (RrhContactoEmergenciaDto emergencyContactDto1:emergencyContactDto) {
            rrhContactoEmergencia = RrhContactoEmergencia.builder()
                    .idContactoEmergencia(emergencyContactDto1.getIdContactoemergencia())
                    .idEmpleado(idEmployee)
                    .idParentesco(emergencyContactDto1.getIdParentesco())
                    .nombreContactoEmergencia(emergencyContactDto1.getNombreContactoemergencia())
                    .celularContactoEmergencia(emergencyContactDto1.getCelularContactoemergencia())
                    .telefonoContactoEmergencia(emergencyContactDto1.getTelefonoContactoemergencia())
                    .build();
            emergencyContactList.add(rrhContactoEmergencia);
        }
        return rrhContactoEmergenciaRepository.saveAll(emergencyContactList);
    }


}
