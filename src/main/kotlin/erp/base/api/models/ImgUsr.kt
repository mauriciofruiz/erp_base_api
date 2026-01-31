package erp.base.api.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("IMGUSR")
data class ImgUsr(
    @Id
    @Column("ID_IMGUSR")
    @JsonProperty("imageUserId")
    val idImgUsr: Int,
    @Column("ID_USR")
    @JsonProperty("userId")
    val idUsr: Int? = null,
    @Column("IMAGEN_IMGUSR")
    @JsonProperty("image")
    val imagenImgUsr: ByteArray? = null,
)
