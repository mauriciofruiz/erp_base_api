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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table("TBA_DEPOSITO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaDeposito {
    @Id
    @Column("ID_DEPOSITO")
    @JsonProperty("depositId")
    private Integer idDeposito;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;

    @Column("ID_BANCO")
    @JsonProperty("bankId")
    private Integer idBanco;

    @Column("ID_USR")
    @JsonProperty("userId")
    private Integer idUsr;

    @Column("ID_ASIENTO")
    @JsonProperty("ledgerId")
    private Integer idAsiento;

    @Column("ID_ESTADODEPOSITO")
    @JsonProperty("depositStatusId")
    private Integer idEstadoDeposito;

    @Column("NUMREF_DEPOSITO")
    @JsonProperty("depositRefNumber")
    private String numRefDeposito;

    @Column("FECHA_DEPOSITO")
    @JsonProperty("depositDate")
    private LocalDate fechaDeposito;

    @Column("MONTO_DEPOSITO")
    @JsonProperty("depositAmount")
    private BigDecimal montoDeposito;

    @Column("OBS_DEPOSITO")
    @JsonProperty("depositNotes")
    private String obsDeposito;

    @Column("FECHAREGISTRO_DEPOSITO")
    @JsonProperty("depositRegistrationDate")
    private LocalDateTime fechaRegistroDeposito;

    @Column("FECHAANULA_DEPOSITO")
    @JsonProperty("depositCancellationDate")
    private LocalDateTime fechaAnulacionDeposito;

    @Column("MOTIVOANULA_DEPOSITO")
    @JsonProperty("depositCancellationReason")
    private String motivoAnulacionDeposito;

    @Column("FECHACONCILIA_DEPOSITO")
    @JsonProperty("depositReconciliationDate")
    private LocalDate fechaConciliacionDeposito;

    @Column("REFERENCIA_DEPOSITO")
    @JsonProperty("depositReference")
    private String referenciaDeposito;
}
