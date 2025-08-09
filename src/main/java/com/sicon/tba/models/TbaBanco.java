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

@Table("TBA_BANCO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaBanco {
    @Id
    @Column("ID_BANCO")
    @JsonProperty("bankId")
    private Integer idBanco;

    @Column("ID_CUENTA")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @Column("ID_TIPOCUENTABANCARIA")
    @JsonProperty("bankAccountTypeId")
    private Integer idTipoCuentaBancaria;

    @Column("NOMBRE_BANCO")
    @JsonProperty("bankName")
    private String nombreBanco;

    @Column("NUMEROCUENTA_BANCO")
    @JsonProperty("bankAccountNumber")
    private String numeroCuentaBanco;

    @Column("SALDODISPONIBLE_BANCO")
    @JsonProperty("availableBalance")
    private BigDecimal saldoDisponibleBanco;

    @Column("SALDOCONTABLE_BANCO")
    @JsonProperty("accountBalance")
    private BigDecimal saldoContableBanco;

    @Column("CONTACTO_BANCO")
    @JsonProperty("bankContact")
    private String contactoBanco;

    @Column("TITULOCONTACTO_BANCO")
    @JsonProperty("bankContactTitle")
    private String tituloContactoBanco;

    @Column("TELEFONOCONTACTO_BANCO")
    @JsonProperty("bankContactPhone")
    private String telefonoContactoBanco;

    @Column("NUMCHEQUEIMP_BANCO")
    @JsonProperty("printedCheckNumber")
    private Integer numeroChequeImpresoBanco;

    @Column("ULTIMONUMCHEQUE_BANCO")
    @JsonProperty("lastCheckNumber")
    private Integer ultimoNumeroChequeBanco;

    @Column("ESCAJACHICA_BANCO")
    @JsonProperty("isPettyCash")
    private Boolean esCajaChicaBanco;

    @Column("ESTADO_BANCO")
    @JsonProperty("bankState")
    private Boolean estadoBanco;

    @Column("CHEQUERECUPERADO_BANCO")
    @JsonProperty("recoveredCheck")
    private String chequeRecuperadoBanco;

    @Column("TXTDEPOSITO_BANCO")
    @JsonProperty("depositText")
    private String textoDepositoBanco;

    @Column("TXTTARJETA_BANCO")
    @JsonProperty("cardText")
    private String textoTarjetaBanco;

    @Column("SALDOINICIAL_BANCO")
    @JsonProperty("initialBalance")
    private BigDecimal saldoInicialBanco;

    @Column("ID_SUCURSAL")
    @JsonProperty("branchId")
    private Integer idSucursal;
}
