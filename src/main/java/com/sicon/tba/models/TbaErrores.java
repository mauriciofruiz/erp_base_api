package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_ERRORES")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaErrores {
    @Id
    @Column("ID_ERRORES")
    @JsonProperty("errorId")
    private Integer idErrores;

    @Column("DESCRIPCIONERROR_ERRORES")
    @JsonProperty("errorDescription")
    private String descripcionErrorErrores;

    @Column("DESCRIPCION_ERRORES")
    @JsonProperty("description")
    private String descripcionErrores;

    @Column("ERROR_ERRORES")
    @JsonProperty("error")
    private String errorErrores;

    @Column("IDDOCUMENTO_ERRORES")
    @JsonProperty("documentId")
    private Integer idDocumentoErrores;

    @Column("MODULO_ERRORES")
    @JsonProperty("module")
    private String moduloErrores;

    @Column("REVISADO_ERRORES")
    @JsonProperty("reviewed")
    private boolean revisadoErrores;

    @Column("TIPODOCUMENTO_ERRORES")
    @JsonProperty("documentType")
    private String tipoDocumentoErrores;
}
