package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

    @CreatedBy
    @Column("id_usuario_creacion")
    @JsonProperty("creationUserId")
    private Integer idUsuarioCreacion;

    @LastModifiedBy
    @Column("id_usuario_modificacion")
    @JsonProperty("modificationUserId")
    private Integer idUsuarioModificacion;

    @CreatedDate
    @Column("fecha_creacion")
    @JsonProperty("registrationDate")
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    @Column("fecha_modificacion")
    @JsonProperty("modificationDate")
    private LocalDateTime fechaModificacion;
}