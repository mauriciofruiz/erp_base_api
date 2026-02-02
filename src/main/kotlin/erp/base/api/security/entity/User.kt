package erp.base.api.security.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Table("usuario")
data class User(
    @Id
    @Column("id_usr")
    @JsonProperty("userId")
    var idUsr: Int? = null,

    @Column("id_sucursal")
    @JsonProperty("branchId")
    var idSucursal: Int? = null,

    @Column("id_persona")
    @JsonProperty("personId")
    var idPersona: Int? = null,

    @Column("nick_usr")
    @JsonProperty("nick")
    var nickUsr: String? = null,

    @Column("pass_usr")
    @JsonProperty("password")
    var passUsr: String? = null,

    @Column("fechaingreso_usr")
    @JsonProperty("entryDate")
    var fechaIngresoUsr: LocalDateTime? = null,

    @Column("fechainactivacion_usr")
    @JsonProperty("deactivationDate")
    var fechaInactivacionUsr: LocalDateTime? = null,

    @Column("horainicio_usr")
    @JsonProperty("startTime")
    var horaInicioUsr: String? = null,

    @Column("horafin_usr")
    @JsonProperty("endTime")
    var horaFinUsr: String? = null,

    @Column("tiempo_usr")
    @JsonProperty("time")
    var tiempoUsr: Int? = null,

    @Column("diasmaximo_usr")
    @JsonProperty("maxDays")
    var diasmaximoUsr: Int? = null,

    @Column("id_caja")
    @JsonProperty("cashId")
    var idCaja: Int? = null,

    @Column("coddesbloqueo_usr")
    @JsonProperty("unlockCode")
    var codigoDesbloqueoUsr: String? = null,

    @Column("estado_usr")
    @JsonProperty("state")
    var estadoUsr: Boolean? = null,

    @Column("img_usr")
    @JsonProperty("photo")
    var imgUsr: String? = null,
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()
    override fun getPassword(): String? = passUsr
    override fun getUsername(): String? = nickUsr
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
