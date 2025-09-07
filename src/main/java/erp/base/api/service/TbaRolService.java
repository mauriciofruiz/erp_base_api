package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TbaRol;
import erp.base.api.repository.TbaRolRepository;
import erp.base.api.utils.Paginacion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TbaRolService {

    private final TbaRolRepository tbaRolRepository;

    public Mono<TbaRol> createRole(TbaRol tbaRol) {
        //tbaRol.setIdRol(null);
        return tbaRolRepository.save(tbaRol);
    }

    public Mono<TbaRol> updateRole(Integer id, TbaRol tbaRol) {
        return tbaRolRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)))
                .flatMap(role -> {
                    role.setIdRol(id);
                    role.setNombreRol(tbaRol.getNombreRol());
                    role.setDescripcionRol(tbaRol.getDescripcionRol());
                    role.setHoraInicioRol(tbaRol.getHoraInicioRol());
                    role.setHoraFinRol(tbaRol.getHoraFinRol());
                    role.setTiempoRol(tbaRol.getTiempoRol());
                    return tbaRolRepository.save(role);
                });
    }

    public Mono<Void> deleteRole(Integer id) {
        return tbaRolRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)))
                .flatMap(tbaRolRepository::delete);
    }

    public Mono<TbaRol> getRoleById(Integer id) {
        return tbaRolRepository.findById(id).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.ROLE_NOT_FOUND + id)));
    }

    public Flux<TbaRol> getAllRoles() {
        return tbaRolRepository.findAll();
    }

    public Mono<Paginacion<TbaRol>> getRequestBySearchParams(
            Integer page,
            Integer limit,
            String sortDirection,
            String sortfield
    ) {
        Flux<TbaRol> rolDtoFlux = tbaRolRepository.getBySearchParams(sortfield, sortDirection, limit ,(page - 1) * limit );
        Mono<Long> total = tbaRolRepository.getCountBySearchParams();
        return total.flatMap(totalCount -> rolDtoFlux.collectList().map(rolDtoList -> new Paginacion<>(rolDtoList, page, limit, totalCount.intValue())));
    }
}
