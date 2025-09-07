package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DocumentoDto {
    @JsonProperty("documentId")
    private Integer idDocumento;

    @JsonProperty("numberDocument")
    private String numeroDocumento;

    @JsonProperty("documentTypeId")
    private String tipoComprobante;

    @JsonProperty("documentTypeName")
    private String nombreTipoDoc;

    @JsonProperty("documentStateId")
    private Integer estadoDocumento;

    @JsonProperty("documentComercialName")
    private String nombreComercial;

    @JsonProperty("documentIdentification")
    private String identificacionDocumento;

    @JsonProperty("issueDate")
    private LocalDateTime fechaEmision;

    @JsonProperty("accessKey")
    private String claveAcceso;
}
