package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_TIPOPROVEEDOR")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaTipoProveedor {
    @Id
    @Column("ID_TIPOPROV")
    @JsonProperty("vendorTypeId")
    private Integer idTipoProveedor;

    @Column("NOMBRE_TIPOPROV")
    @JsonProperty("vendorTypeName")
    private String nombreTipoProveedor;
}
