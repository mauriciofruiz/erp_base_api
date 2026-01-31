package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CONTACTOEMERGENCIA")
data class ContactoEmergencia(
    @Id
    @Column("ID_CONTACTOEMERGENCIA")
    @JsonProperty("contactEmergencyId")
    val idContactoEmergencia: Int? = null,

    @Column("ID_EMPLEADO")
    @JsonProperty("employeeId")
    val idEmpleado: Int? = null,

    @Column("ID_PARENTESCO")
    @JsonProperty("kinId")
    val idParentesco: Int? = null,

    @Column("NOMBRE_CONTACTOEMERGENCIA")
    @JsonProperty("nameContactEmergency")
    val nombreContactoEmergencia: String? = null,

    @Column("CELULAR_CONTACTOEMERGENCIA")
    @JsonProperty("cellphoneContactEmergency")
    val celularContactoEmergencia: String? = null,

    @Column("TELEFONO_CONTACTOEMERGENCIA")
    @JsonProperty("phoneContactEmergency")
    val telefonoContactoEmergencia: String? = null,
)
