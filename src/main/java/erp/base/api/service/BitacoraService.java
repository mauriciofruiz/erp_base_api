package erp.base.api.service;


import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.BitacoraDto;
import erp.base.api.enums.*;
import erp.base.api.exception.CustomException;
import erp.base.api.models.Bitacora;
import erp.base.api.models.ExcepcionesClienteDescuento;
import erp.base.api.models.TbaCliente;
import erp.base.api.models.TbaCreditoCliente;
import erp.base.api.repository.BitacoraRepository;
import erp.base.api.repository.TbaClienteRepository;
import erp.base.api.repository.TbaCreditoClienteRepository;
import erp.base.api.repository.custom.BitacoraCustom;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.UtilsBitacora;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static erp.base.api.utils.Utils.currentZonedDateTimeEcuador;

@Service
@RequiredArgsConstructor
public class BitacoraService {

    private final BitacoraRepository bitacoraRepository;
    private final BitacoraCustom bitacoraCustom;
    private final TbaClienteRepository tbaClienteRepository;
    private final TbaCreditoClienteRepository tbaCreditoClienteRepository;
    private final ExcepcionesClienteDescuentoService excepcionesClienteDescuentoService;

    public Mono<Bitacora> saveTransactionLog(Bitacora bitacora, String ip, String hostName) {
        bitacora.setFecha(currentZonedDateTimeEcuador());
        bitacora.setIpDispositivo(ip);
        bitacora.setNombreDispositivo(hostName);
        return bitacoraRepository.save(bitacora);
    }

    public Flux<BitacoraDto> findAllByIdAndTable(Integer id, String table) {
        return bitacoraRepository.findAllByIdAndTable(id, table);
    }

    public Mono<Paginacion<BitacoraDto>> getTransactionLogByClient(Integer clientId, Integer page, Integer limit, String sortDirection, String sortField) {
        Mono<TbaCliente> client = tbaClienteRepository.findById(clientId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.CLIENT_NOT_FOUND)));
        Mono<TbaCreditoCliente> creditClient = tbaCreditoClienteRepository.findByIdCliente(clientId).switchIfEmpty(Mono.just(new TbaCreditoCliente()));
        Mono<List<ExcepcionesClienteDescuento>> exceptions = excepcionesClienteDescuentoService.getExceptionsByClientId(Long.valueOf(clientId)).collectList();
        return Mono.zip(client, creditClient,exceptions)
                .flatMap(tuple -> {
                    List<Integer> exceptionsIds = new ArrayList<>();
                    tuple.getT3().forEach(ex -> {
                        exceptionsIds.add(ex.getIdExcepcion());
                    });
                    List<Integer> ids = new ArrayList<>(List.of(tuple.getT1().getIdCliente(), tuple.getT1().getIdPersona(), tuple.getT2().getIdCreditoCliente() != null ? tuple.getT2().getIdCreditoCliente() : 0));
                    ids.addAll(exceptionsIds);
                    Flux<BitacoraDto> bitacoraDtoFlux = bitacoraCustom.getTransactionLogByClient(ids, sortField, sortDirection, limit, (page - 1) * limit)
                            .map(this::transformBitacora);
                    Mono<Long> total = bitacoraCustom.getCountTransactionLogByClient(ids);
                    return total.flatMap(totalCount -> bitacoraDtoFlux.collectList().map(bitacora -> new Paginacion<>(bitacora, page, limit, totalCount.intValue())));
                });
    }

    private BitacoraDto transformBitacora(BitacoraDto bitacora) {
        String campoBitacora = bitacora.getCampo();
        String oldValue = bitacora.getOldValue();
        String newValue = bitacora.getNewValue();

        switch (campoBitacora) {
            case "id_genero":
                // Traducir oldValue y newValue utilizando el enum GeneroEnum
                String translatedOldValue = oldValue != null ? GeneroEnum.getDescriptionById(Integer.valueOf(oldValue)) : "";
                String translatedNewValue = newValue != null ? GeneroEnum.getDescriptionById(Integer.valueOf(newValue)) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValue);
                bitacora.setNewValue(translatedNewValue);
                break;
            case "id_estadocivil":
                // Traducir oldValue y newValue utilizando el enum EstadoCivilEnum
                String translatedOldValueEstadoCivil = oldValue != null ? EstadoCivilEnum.getDescriptionById(Integer.valueOf(oldValue)) : "";
                String translatedNewValueEstadoCivil = newValue != null ? EstadoCivilEnum.getDescriptionById(Integer.valueOf(newValue)) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueEstadoCivil);
                bitacora.setNewValue(translatedNewValueEstadoCivil);
                break;
            case "id_tipoidfiscal":
                // Traducir oldValue y newValue utilizando el enum TipoIdFiscalEnum
                String translatedOldValueTipoIdFiscal = oldValue != null ? TipoFiscalEnum.getTipoIdentificacionById(oldValue) : "";
                String translatedNewValueTipoIdFiscal = newValue != null ? TipoFiscalEnum.getTipoIdentificacionById(newValue) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueTipoIdFiscal);
                bitacora.setNewValue(translatedNewValueTipoIdFiscal);
                break;
            case "faccontado_cliente":
                // Traducir oldValue y newValue a boolean
                String translatedOldValueFacContado = oldValue != null ? oldValue.equals("true") ? "Sí" : "No" : "";
                String translatedNewValueFacContado = newValue != null ? newValue.equals("true") ? "Sí" : "No" : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueFacContado);
                bitacora.setNewValue(translatedNewValueFacContado);
                break;
            case "id_categoriacliente":
                // Traducir oldValue y newValue utilizando el enum CategoriaClienteEnum
                String translatedOldValueCategoriaCliente = oldValue != null ? CategoriaClienteEnum.getDescriptionById(Integer.valueOf(oldValue)) : "";
                String translatedNewValueCategoriaCliente = newValue != null ? CategoriaClienteEnum.getDescriptionById(Integer.valueOf(newValue)) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueCategoriaCliente);
                bitacora.setNewValue(translatedNewValueCategoriaCliente);
                break;
            case "estado_cliente":
                // Traducir oldValue y newValue a boolean
                String translatedOldValueEstadoCliente = oldValue != null ? oldValue.equals("true") ? "Activo" : "Inactivo" : "";
                String translatedNewValueEstadoCliente = newValue != null ? newValue.equals("true") ? "Activo" : "Inactivo" : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueEstadoCliente);
                bitacora.setNewValue(translatedNewValueEstadoCliente);
                break;
            case "id_tipocontribuyente":
                // Traducir oldValue y newValue a boolean
                String translatedOldValueTipoContribuyente = oldValue != null ? TipoConstribuyenteEnum.getNameById(oldValue) : "";
                String translatedNewValueTipoContribuyente = newValue != null ? TipoConstribuyenteEnum.getNameById(newValue) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueTipoContribuyente);
                bitacora.setNewValue(translatedNewValueTipoContribuyente);
                break;
            case "id_retencionfuente":
                // Traducir oldValue y newValue a boolean
                String translatedOldValueRetencionFuente = oldValue != null ? RetencionFuenteEnum.getDescriptionById(oldValue) : "";
                String translatedNewValueRetencionFuente = newValue != null ? RetencionFuenteEnum.getDescriptionById(newValue) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueRetencionFuente);
                bitacora.setNewValue(translatedNewValueRetencionFuente);
                break;
            case "id_retencioniva":
                // Traducir oldValue y newValue a boolean
                String translatedOldValueRetencionIva = oldValue != null ? RetencionIVAEnum.getDescriptionById(Integer.valueOf(oldValue)) : "";
                String translatedNewValueRetencionIva = newValue != null ? RetencionIVAEnum.getDescriptionById(Integer.valueOf(newValue)) : "";

                // Actualizar los valores en la bitácora
                bitacora.setOldValue(translatedOldValueRetencionIva);
                bitacora.setNewValue(translatedNewValueRetencionIva);
                break;
            default:
                break;
        }

        String translatedCampo = UtilsBitacora.CAMPO_BITACORA_TRANSLATIONS.getOrDefault(bitacora.getCampo(), bitacora.getCampo());
        bitacora.setCampo(translatedCampo);
        return bitacora;
    }
}
