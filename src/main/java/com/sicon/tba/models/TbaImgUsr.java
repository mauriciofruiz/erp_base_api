package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_IMGUSR")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaImgUsr {
    @Id
    @Column("ID_IMGUSR")
    @JsonProperty("imageUserId")
    private Integer idImgUsr;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("IMAGEN_IMGUSR")
    @JsonProperty("image")
    private byte[] imagenImgUsr;
}
