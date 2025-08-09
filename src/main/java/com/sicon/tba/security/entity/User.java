package com.sicon.tba.security.entity;

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

@Table("TBA_USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("ID_PERSONA")
    @JsonProperty("personId")
    private Integer idPersona;

    @Column("NICK_USR")
    @JsonProperty("nick")
    private String nickUsr;

    @Column("PASS_USR")
    @JsonProperty("password")
    private String passUsr;

    @Column("CARGO_USR")
    @JsonProperty("position")
    private Integer cargoUsr;

    @Column("FECHAINGRESO_USR")
    @JsonProperty("entryDate")
    private LocalDateTime fechaIngresoUsr;

    @Column("FECHAINACTIVACION_USR")
    @JsonProperty("deactivationDate")
    private LocalDateTime fechaInactivacionUsr;

    @Column("HORAINICIO_USR")
    @JsonProperty("startTime")
    private String horaInicioUsr;

    @Column("HORAFIN_USR")
    @JsonProperty("endTime")
    private String horaFinUsr;

    @Column("TIEMPO_USR")
    @JsonProperty("time")
    private Integer tiempoUsr;

    @Column("id_caja")
    @JsonProperty("cashId")
    private Integer idCaja;

    @Column("CODDESBLOQUEO_USR")
    @JsonProperty("unlockCode")
    private String codigoDesbloqueoUsr;

    @Column("ESTADO_USR")
    @JsonProperty("state")
    private Boolean estadoUsr;

    @Column("IMG_USR")
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
}
