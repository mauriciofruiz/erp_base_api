package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("INDICEFINANCIERO")
data class IndiceFinanciero(
    @Id
    @Column("ID_INDICEFINANCIERO")
    @JsonProperty("financialIndexId")
    val idIndiceFinanciero: Int,
    @Column("NOMBRE_INDICEFINANCIERO")
    @JsonProperty("financialIndexName")
    val nombreIndiceFinanciero: String? = null,
)
