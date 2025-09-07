package erp.base.api.service;

import erp.base.api.dto.RrhCargasFamiliaresDto;
import erp.base.api.models.rrh.RrhCargaFamiliar;
import erp.base.api.repository.rrh.RrhCargaFamiliarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RrhCargaFamiliarService {


    private final RrhCargaFamiliarRepository rrhCargaFamiliarRepository;
    private RrhCargaFamiliar rrhCargaFamiliar;

    public Flux<RrhCargaFamiliar> createEmployeeFamilyBurden(List<RrhCargasFamiliaresDto> familyBurdensDtoList, Integer idEmpleado) {
        List<RrhCargaFamiliar> familyBurdenList = new ArrayList<>();
        for (RrhCargasFamiliaresDto familyBurdenFlux : familyBurdensDtoList) {
                rrhCargaFamiliar = RrhCargaFamiliar.builder()
                        .idCargaFml(familyBurdenFlux.getIdCargafml())
                        .idEmpleado(idEmpleado)
                        .idParentesco(familyBurdenFlux.getIdParentesco())
                        .nombreCargaFml(familyBurdenFlux.getNombreCargafml())
                        .cedulaCargaFml(familyBurdenFlux.getCedulaCargafml())
                        .capacidadEspecialCargaFml(familyBurdenFlux.getCapacidadespecialCargafml())
                        .fechaNacimientoCargaFml(familyBurdenFlux.getFechanacimientoCargafml())
                        .activaCargaFml(Boolean.TRUE) // Siempre True por qué se activa a esta carga
                        .build();
                familyBurdenList.add(rrhCargaFamiliar);

        }
        return rrhCargaFamiliarRepository.saveAll(familyBurdenList);
    }
}






