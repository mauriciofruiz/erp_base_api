package erp.base.api.security.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Table("usuario")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column("id_usr")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("id_sucursal")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("id_persona")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("nick_usr")
    @JsonProperty("nick")
    private String nickUsr;

    @Column("pass_usr")
    @JsonProperty("password")
    private String passUsr;

    @Column("cargo_usr")
    @JsonProperty("position")
    private Integer cargoUsr;

    @Column("fechaingreso_usr")
    @JsonProperty("entryDate")
    private LocalDateTime fechaIngresoUsr;

    @Column("fechainactivacion_usr")
    @JsonProperty("deactivationDate")
    private LocalDateTime fechaInactivacionUsr;

    @Column("horainicio_usr")
    @JsonProperty("startTime")
    private String horaInicioUsr;

    @Column("horafin_usr")
    @JsonProperty("endTime")
    private String horaFinUsr;

    @Column("tiempo_usr")
    @JsonProperty("time")
    private Integer tiempoUsr;

    @Column("creditomaximo_usr")
    @JsonProperty("maxCredit")
    private java.math.BigDecimal creditomaximoUsr;

    @Column("diasmaximo_usr")
    @JsonProperty("maxDays")
    private Integer diasmaximoUsr;

    @Column("id_caja")
    @JsonProperty("cashId")
    private Integer idCaja;

    @Column("coddesbloqueo_usr")
    @JsonProperty("unlockCode")
    private String codigoDesbloqueoUsr;

    @Column("estado_usr")
    @JsonProperty("state")
    private Boolean estadoUsr;

    @Column("img_usr")
    @JsonProperty("photo")
    private String imgUsr;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return passUsr;
    }

    @Override
    public String getUsername() {
        return nickUsr;
    }

    public String getImgUsr() {
        return imgUsr;
    }

    public Integer getUserId() {
        return idUsr;
    }

    public Integer getPersonId() {
        return idPersona;
    }

    public String getHoraInicioUsr() {
        return horaInicioUsr;
    }

    public Integer getCargoUsr() {
        return cargoUsr;
    }

    public String getHoraFinUsr() {
        return horaFinUsr;
    }

    public Integer getTiempoUsr() {
        return tiempoUsr;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getIdCaja() {
        return idCaja;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public java.math.BigDecimal getCreditomaximoUsr() {
        return creditomaximoUsr;
    }

    public Integer getDiasmaximoUsr() {
        return diasmaximoUsr;
    }
}
