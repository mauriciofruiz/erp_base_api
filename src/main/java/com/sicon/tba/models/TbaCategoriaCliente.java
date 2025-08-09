package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("TBA_CATEGORIACLIENTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaCategoriaCliente {
    @Id
    @Column("ID_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryId")
    private Integer idCategoriaCliente;

    @Column("NOMBRE_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryName")
    private String nombreCategoriaCliente;

    @Column("DESCUENTO_CATEGORIACLIENTE")
    @JsonProperty("clientCategoryDiscount")
    private BigDecimal descuentoCategoriaCliente;

    @Column("CATEGORIAESPECIAL_CATEGORIACLIENTE")
    @JsonProperty("specialCategory")
    private Boolean categoriaEspecialCategoriaCliente;

    @Column("ACTIVOCATEGORIA_CATEGORIACLIENTE")
    @JsonProperty("categoryActive")
    private Boolean activoCategoriaCliente;
}
