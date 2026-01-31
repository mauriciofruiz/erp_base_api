package erp.base.api.models;

        import com.fasterxml.jackson.annotation.JsonProperty;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import org.springframework.data.annotation.Id;
        import org.springframework.data.relational.core.mapping.Column;
        import org.springframework.data.relational.core.mapping.Table;

@Table("DOCUMENTOVERIFICACION")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDocumentoVerificacion {
    @Id
    @Column("ID_DOCUMENTOVERIFICACION")
    @JsonProperty("verificationDocumentId")
    private Integer idDocumentoVerificacion;

    @Column("NUMERO_DOCUMENTOVERIFICACION")
    @JsonProperty("verificationDocumentNumber")
    private Integer numeroDocumentoVerificacion;

    @Column("TIPO_DOCUMENTOVERIFICACION")
    @JsonProperty("verificationDocumentType")
    private String tipoDocumentoVerificacion;
}
