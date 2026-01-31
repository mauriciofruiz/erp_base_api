package erp.base.api.models.rrh

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CODIGOSECTORIAL")
data class CodigoSectorial(
    @Id
    @Column("ID_CODIGOSECTORIAL")
    @JsonProperty("sectoralCodeId")
    val idCodigoSectorial: Int,
    @Column("NOMBRE_CODIGOSECTORIAL")
    @JsonProperty("sectoralCodeName")
    val nombreCodigoSectorial: String? = null,
    @Column("COD_CODIGOSECTORIAL")
    @JsonProperty("sectoralCodeCod")
    val codigoCodigoSectorial: String? = null,
)
