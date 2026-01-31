package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("TIPOIDENTIFICACIONFISCAL")
data class TipoIdentificacionFiscal(
    @Id
    @Column("ID_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeId")
    var idTipoIdFiscal: String,

    @Column("TIPOTRANSACCION_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeTransaction")
    var tipoTransaccionTipoIdFiscal: String? = null,

    @Column("TIPOIDENTIFICACION_TIPOIDFISCAL")
    @JsonProperty("fiscalIdentificationTypeName")
    var tipoIdentificacionTipoIdFiscal: String? = null,
)
