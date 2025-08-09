package com.sicon.tba.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.TbaTipoProveedorDto;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.models.SustentoTributario;
import com.sicon.tba.models.TbaTipoProveedor;
import com.sicon.tba.repository.SustentoTributarioRepository;
import com.sicon.tba.repository.TbaTipoProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SustentoTributarioService {


    private final SustentoTributarioRepository sustentoTributarioRepository;

    /**
     * Método para obtener todas los tipos de sustento tributario
     *
     * @return  Flux<SustentoTributario> objeto que contiene los datos del tipo de sustento tributario
     */
    public Flux<SustentoTributario> getAllSustentoTributario() {
        return sustentoTributarioRepository.findAll();
    }
}
