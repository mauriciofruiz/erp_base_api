package com.sicon.tba.service;

import com.sicon.tba.dto.DocumentoDto;
import com.sicon.tba.repository.DocumentoRepository;
import com.sicon.tba.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase que contiene los servicios de Documento
 */
@Service
@RequiredArgsConstructor
public class DocumentoService {
    private final DocumentoRepository documentRepository;

    public Mono<Paginacion<DocumentoDto>> getRequestBySearchParams(
            String documentNumber,
            String documentType,
            String name,
            String identification,
            String accesKey,
            Boolean isAuthorized,
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<DocumentoDto> documentoDtoFlux = documentRepository.getBySearchParams(documentNumber, documentType, name, identification, accesKey, isAuthorized, sortfield, sortDirection, limit, (page - 1) * limit);
        Mono<Long> total = documentRepository.getCountBySearchParams(documentNumber, documentType, name, identification, accesKey, isAuthorized);
        return total.flatMap(totalCount -> documentoDtoFlux.collectList().map(documentoList -> new Paginacion<>(documentoList, page, limit, totalCount.intValue())));
    }

}
