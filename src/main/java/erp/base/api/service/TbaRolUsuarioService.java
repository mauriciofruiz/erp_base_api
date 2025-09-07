package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.RolResponseDto;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaRol;
import erp.base.api.models.TbaRolUsuario;
import erp.base.api.models.TbaUsuario;
import erp.base.api.repository.TbaRolUsuarioRepository;
import erp.base.api.repository.TbaUsuarioRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class TbaRolUsuarioService {

    private final TbaRolUsuarioRepository tbaRolUsuarioRepository;
    private final TbaUsuarioRepository tbaUsuarioRepository;
    private final TbaRolService tbaRolService;

    public Mono<TbaRolUsuario> create(TbaRolUsuario tbaRolUsuario) {
        return validateAndSaveUserRole(null, tbaRolUsuario);
    }

    public Mono<TbaRolUsuario> update(Integer id, TbaRolUsuario tbaRolUsuario) {
        return validateAndSaveUserRole(id, tbaRolUsuario);
    }

    private Mono<TbaRolUsuario> validateAndSaveUserRole(Integer id, TbaRolUsuario tbaRolUsuario) {
        Mono<TbaRolUsuario> tbaRolUsuarioMono = id != null ? tbaRolUsuarioRepository.findById(id) : Mono.just(new TbaRolUsuario());
        Mono<TbaUsuario> tbaUsuarioMono = tbaUsuarioRepository.findById(tbaRolUsuario.getIdUsuario());
        Mono<TbaRol> tbaRolMono = tbaRolService.getRoleById(tbaRolUsuario.getIdRol());
        return Mono.zip(tbaRolUsuarioMono, tbaUsuarioMono, tbaRolMono)
                .flatMap(tuple -> {
                    tuple.getT1().setIdRolUsuario(id);
                    tuple.getT1().setIdUsuario(tuple.getT2().getIdUsr());
                    tuple.getT1().setIdRol(tuple.getT3().getIdRol());
                    tuple.getT1().setEstadoRolUsuario(tbaRolUsuario.getEstadoRolUsuario());
                    return tbaRolUsuarioRepository.save(tuple.getT1());
                });
    }

    public Mono<Void> deleteUserRole(Integer id) {
        return tbaRolUsuarioRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ROLE_NOT_FOUND + id)))
                .flatMap(tbaRolUsuarioRepository::delete);
    }

    public Mono<TbaRolUsuario> findById(Integer id) {
        return tbaRolUsuarioRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ROLE_NOT_FOUND + id)));
    }

    public Flux<TbaRolUsuario> findAll() {
        return tbaRolUsuarioRepository.findAll();
    }

    public Flux<TbaRolUsuario> findRolByUserId(Integer id) {
        return tbaRolUsuarioRepository.findByIdUsuario(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_ROLE_NOT_FOUND + id)));
    }

    public Flux<TbaRolUsuario> updateRolByUser(List<TbaRolUsuario> detailRol ) {
        return tbaRolUsuarioRepository.saveAll(detailRol).onErrorMap(e -> {
                    log.error("Error al guardar los roles por usuario");
                    return new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
                });
    }


}
