package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("INV_ROTACIONPRODUCTO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InvRotacionProducto {
    @Id
    @Column("ID_ROTACIONPRODUCTO")
    @JsonProperty("rotationProductId")
    private Integer idRotacionProducto;

    @Column("NOMBRE_ROTACIONPRODUCTO")
    @JsonProperty("rotationProductName")
    private String nombreRotacionProducto;
}
