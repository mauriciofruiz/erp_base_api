package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("TBA_TRANSPORTE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class TbaTransporte extends BaseEntity{
    @Id
    @Column("ID_TRANSPORTE")
    @JsonProperty("transportId")
    private Integer idTransporte;

    @Column("ID_TIPOVEHICULO")
    @JsonProperty("vehicleTypeId")
    private Integer idTipoVehiculo;

    @Column("NOMBRECONTACTO_TRANSPORTE")
    @Size(max = 50, message = "El nombre del contacto no puede tener más de 50 caracteres")
    @JsonProperty("contactName")
    private String nombreContacto;

    @Column("IDENTCONTACTO_TRANSPORTE")
    @Size(max = 16, message = "La identificación del contacto no puede tener más de 16 caracteres")
    @JsonProperty("contactId")
    private String identificacionContacto;

    @Column("NUMCONTACTO_TRANSPORTE")
    @Size(max = 200, message = "El número de contacto no puede tener más de 200 caracteres")
    @JsonProperty("contactNumber")
    private String numeroContacto;

    @Column("PLACA_TRANSPORTE")
    @Size(max = 8, message = "La placa del transporte no puede tener más de 8 caracteres")
    @JsonProperty("licensePlate")
    private String placaTransporte;

    @Column("SECTOR_TRANSPORTE")
    @Size(max = 50, message = "El sector del transporte no puede tener más de 50 caracteres")
    @JsonProperty("sector")
    private String sectorTransporte;

    @Column("PROVINCIA_TRANSPORTE")
    @Size(max = 50, message = "La provincia del transporte no puede tener más de 50 caracteres")
    @JsonProperty("province")
    private String provinciaTransporte;

    @Column("DET_TRANSPORTE")
    @Size(max = 200, message = "El detalle del transporte no puede tener más de 200 caracteres")
    @JsonProperty("details")
    private String detalleTransporte;

    @Column("PESOMIN_TRANSPORTE")
    @JsonProperty("minWeight")
    private BigDecimal pesoMinimoTransporte;

    @Column("PESOMAX_TRANSPORTE")
    @JsonProperty("maxWeight")
    private BigDecimal pesoMaximoTransporte;

    @Column("MATRICULA_TRANSPORTE")
    @Size(max = 8, message = "La matrícula del transporte no puede tener más de 8 caracteres")
    @JsonProperty("registration")
    private String matriculaTransporte;

    @Column("INTERNO_TRANSPORTE")
    @JsonProperty("internal")
    private Boolean internoTransporte;

    @Column("INACTIVO_TRANSPORTE")
    @JsonProperty("inactive")
    private Boolean inactivoTransporte;

    @Column("id_tipoidentificacionfiscal")
    @JsonProperty("idTipoIdentificacionFiscal")
    private String idTipoIdentificacionFiscal;

}
