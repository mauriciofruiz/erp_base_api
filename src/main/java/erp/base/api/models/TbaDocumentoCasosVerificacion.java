package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_DOCUMENTOCASOSVERIFICACION")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDocumentoCasosVerificacion {
    @Id
    @Column("ID_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseId")
    private Integer idDocumentoCasosVerificacion;

    @Column("ESTADO_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseState")
    private Integer estadoDocumentoCasosVerificacion;

    @Column("IDCASO_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseIdCase")
    private Integer idCasoDocumentoCasosVerificacion;

    @Column("IDDOCUMENTO_DOCUMENTOCASOSVERIFICACION")
    @JsonProperty("documentVerificationCaseIdDocumento")
    private Integer idDocumentoDocumentoCasosVerificacion;
}
