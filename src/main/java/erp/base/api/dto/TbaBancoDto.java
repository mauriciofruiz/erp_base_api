package erp.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TbaBancoDto {

    @NotNull(message = "La cuenta no puede ser nulo")
    @JsonProperty("accountId")
    private Integer idCuenta;

    @NotNull(message = "El tipo de cuenta no puede ser nulo")
    @JsonProperty("bankAccountTypeId")
    private Integer idTipoCuentaBancaria;

    @NotBlank(message = "El nombre del blanco no puede estar vacía")
    @JsonProperty("bankName")
    private String nombreBanco;

    @NotBlank(message = "El número del banco no puede estar vacío")
    @JsonProperty("bankAccountNumber")
    private String numeroCuentaBanco;

    @NotNull(message = "El saldo disponible del banco no puede ser nulo")
    @JsonProperty("availableBalance")
    private BigDecimal saldoDisponibleBanco;

    @NotNull(message = "El saldo contable del banco no puede ser nulo")
    @JsonProperty("accountBalance")
    private BigDecimal saldoContableBanco;

    @JsonProperty("bankContact")
    private String contactoBanco;

    @JsonProperty("bankContactTitle")
    private String tituloContactoBanco;

    @JsonProperty("bankContactPhone")
    private String telefonoContactoBanco;


    @JsonProperty("printedCheckNumber")
    private Integer numeroChequeImpresoBanco;

    @JsonProperty("lastCheckNumber")
    private Integer ultimoNumeroChequeBanco;


    @JsonProperty("isPettyCash")
    private Boolean esCajaChicaBanco = Boolean.FALSE;


    @JsonProperty("bankState")
    private Boolean estadoBanco = Boolean.TRUE;


   /* @JsonProperty("recoveredCheck")
    private String chequeRecuperadoBanco;
    */

    @JsonProperty("depositText")
    private String textoDepositoBanco;

    @JsonProperty("cardText")
    private String textoTarjetaBanco;

    @Min(value = 0, message = "El saldo inicial del banco no puede ser negativo ni estar vacío")
    @JsonProperty("initialBalance")
    private BigDecimal saldoInicialBanco;

    @NotNull(message = "La sucursal no puede ser nulo")
    @JsonProperty("branchId")
    private Integer idSucursal;
}
