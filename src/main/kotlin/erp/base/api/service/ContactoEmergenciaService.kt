package erp.base.api.service

import erp.base.api.dto.ContactoEmergenciaDto
import erp.base.api.models.rrh.ContactoEmergencia
import erp.base.api.repository.rrh.ContactoEmergenciaRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class ContactoEmergenciaService(private val rrhContactoEmergenciaRepository: ContactoEmergenciaRepository) {

    fun createEmployeeEmergencyContact(emergencyContactDto: List<ContactoEmergenciaDto>, idEmployee: Int): Flux<ContactoEmergencia> {
        val emergencyContactList = emergencyContactDto.map { dto ->
            ContactoEmergencia(
                idContactoEmergencia = dto.idContactoemergencia,
                idEmpleado = idEmployee,
                idParentesco = dto.idParentesco,
                nombreContactoEmergencia = dto.nombreContactoemergencia,
                celularContactoEmergencia = dto.celularContactoemergencia,
                telefonoContactoEmergencia = dto.telefonoContactoemergencia
            )
        }
        return rrhContactoEmergenciaRepository.saveAll(emergencyContactList)
    }
}
