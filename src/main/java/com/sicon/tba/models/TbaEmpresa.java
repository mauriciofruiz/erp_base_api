package com.sicon.tba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("TBA_EMPRESA")
public class TbaEmpresa {

    @Id
    @Column("EMP_ID")
    @JsonProperty("companyId")
    private Long id;

    @Column("EMP_CODIGO")
    @JsonProperty("companyCode")
    private String codigo;

    @Column("EMP_RAZONSOCIAL")
    @JsonProperty("companyName")
    private String razonSocial;

    @Column("EMP_CIUDAD")
    @JsonProperty("companyCity")
    private String ciudad;

    @Column("EMP_DIRECCION")
    @JsonProperty("companyAddress")
    private String direccion;

    @Column("EMP_RUCEMPRESA")
    @JsonProperty("companyRuc")
    private String ruc;

    @Column("EMP_TELEFONO")
    @JsonProperty("companyPhone")
    private String telefono;

    @Column("EMP_CORREOELECTRONICO")
    @JsonProperty("companyEmail")
    private String email;

    @Column("EMP_REPRESENTANTE")
    @JsonProperty("companyRepresentative")
    private String representante;

    @Column("EMP_RUCREPRESENTANTE")
    @JsonProperty("representativeRuc")
    private String rucRepresentante;

    @Column("EMP_NUMDECIMALESPVP")
    @JsonProperty("decimalNumberPvp")
    private Integer numeroDecimalesPvp;

    @Column("EMP_OBLIGADOCONTABILIDAD")
    @JsonProperty("accountingMandatory")
    private Boolean obligadoContabilidad;

    @Column("EMP_CONTRIBUYENTEESPECIAL")
    @JsonProperty("specialContributor")
    private String contribuyenteEspecial;

    @Column("EMP_PIEFIRMAFE")
    @JsonProperty("footerSignatureFe")
    private String pieFirmaFe;

    @Column("EMP_AGENTERETIENE")
    @JsonProperty("agentRetains")
    private Boolean agenteRetiene;

    @Column("EMP_RESOLUCIONAGENTE")
    @JsonProperty("resolutionAgent")
    private String resolucionAgente;

    @Column("EMP_HOST")
    @JsonProperty("host")
    private String host;

}
