package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_ACCESO_DOCUMENTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccesoDocumento {
    @Id
    @Column("ID_ACCESO_DOCUMENTO")
    @JsonProperty("accessDocumentId")
    private Integer idAccesoDocumento;

    @NotNull
    @Column("ID_TIPO_DOCUMENTO")
    @JsonProperty("documentTypeId")
    private Integer idTipoDocumento;

    @NotNull
    @Column("ID_DOCUMENTO")
    @JsonProperty("documentId")
    private Integer idDocumento;

    @NotNull
    @Column("NAME_USUARIO")
    @JsonProperty("userName")
    private String nameUsuario;
}
