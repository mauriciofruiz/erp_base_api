package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaTipoProveedorDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.SustentoTributario;
import erp.base.api.models.TbaTipoProveedor;
import erp.base.api.repository.SustentoTributarioRepository;
import erp.base.api.repository.TbaTipoProveedorRepository;
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
