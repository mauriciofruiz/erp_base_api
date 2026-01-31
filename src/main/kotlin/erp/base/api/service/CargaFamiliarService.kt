package erp.base.api.service

import erp.base.api.dto.CargasFamiliaresDto
import erp.base.api.models.rrh.CargaFamiliar
import erp.base.api.repository.rrh.CargaFamiliarRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CargaFamiliarService(private val rrhCargaFamiliarRepository: CargaFamiliarRepository) {

    fun createEmployeeFamilyBurden(familyBurdensDtoList: List<CargasFamiliaresDto>, idEmpleado: Int): Flux<CargaFamiliar> {
        val familyBurdenList = familyBurdensDtoList.map { familyBurdenFlux ->
            CargaFamiliar(
                idCargaFml = familyBurdenFlux.idCargafml,
                idParentesco = familyBurdenFlux.idParentesco,
                idEmpleado = idEmpleado,
                nombreCargaFml = familyBurdenFlux.nombreCargafml,
                cedulaCargaFml = familyBurdenFlux.cedulaCargafml,
                fechaNacimientoCargaFml = familyBurdenFlux.fechanacimientoCargafml,
                capacidadEspecialCargaFml = familyBurdenFlux.capacidadespecialCargafml,
                archivoCargaFml = null,
                activaCargaFml = true
            )
        }
        return rrhCargaFamiliarRepository.saveAll(familyBurdenList)
    }
}
