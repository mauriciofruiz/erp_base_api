package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaEmpleadoDto;
import erp.base.api.dto.TbaInformacionContractualDto;
import erp.base.api.dto.TbaUsuarioDto;
import erp.base.api.dto.TbaUsuarioRolesDto;
import erp.base.api.enums.RolEnum;
import erp.base.api.models.TbaEmpleado;
import erp.base.api.models.TbaPersona;
import erp.base.api.models.TbaUsuario;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaUsuarioRepository;
import erp.base.api.repository.custom.TbaUsuarioCustom;
import erp.base.api.utils.Paginacion;
import erp.base.api.utils.Utils;
import io.r2dbc.spi.Row;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

/**
 * Clase que contiene los métodos para el manejo de los usuarios
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TbaUsuarioService {

    private final DatabaseClient databaseClient;
    private final TbaUsuarioRepository tbaUsuarioRepository;
    private final TbaUsuarioCustom tbaUsuarioCustom;
    private final TbaRolUsuarioService tbaRolUsuarioService;

    /**
     * Método que actualiza el password de un usuario
     *
     * @param userId       id del usuario
     * @param userResponse data del usuario
     * @return usuario actualizado
     */
    public Mono<TbaUsuario> updateUserPassword(Integer userId, TbaUsuario userResponse) {
        Mono<Boolean> userExists = tbaUsuarioRepository.existsByIdPersona(userId);
        return userExists.flatMap(exists -> {
            Mono<TbaUsuario> tbaUsuarioMono = tbaUsuarioRepository.findById(userId);
            return tbaUsuarioMono.flatMap(user -> {
                try {
                    user.setPassUsr(Utils.encriptKey(userResponse.getPassUsr()));
                } catch (Exception e) {
                    return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, e.getMessage()));
                }
                return tbaUsuarioRepository.save(user);
            });
        });
    }

    /**
     * Método que actualiza un usuario
     *
     * @param userId       id del usuario
     * @param userResponse data del usuario
     * @return usuario actualizado
     */
    public Mono<TbaUsuario> updateUser(Integer userId, TbaUsuarioRolesDto userResponse) {
        Mono<Boolean> userExists = tbaUsuarioRepository.existsByIdPersona(userId).switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + userId)));
        return userExists.flatMap(exists -> {
            Mono<TbaUsuario> tbaUsuarioMono = tbaUsuarioRepository.findById(userId)
                    .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + userId)));
            return tbaUsuarioMono.flatMap(user -> {
                            validateUpdateFields(user, userResponse);
                            return tbaUsuarioRepository.save(user)
                                    .flatMap(user1 -> tbaRolUsuarioService.updateRolByUser(userResponse.getDetailRol()).collectList())
                                    .then(Mono.just(user))
                                    .onErrorResume(e -> Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage())));
                    }
            );
        });
    }

    private void validateUpdateFields(TbaUsuario user, TbaUsuarioRolesDto userResponse) {
        if (userResponse.getNickUsr() != null && !userResponse.getNickUsr().isEmpty()) {
            if (!user.getNickUsr().equals(userResponse.getNickUsr())) {
                boolean isValidNic = validateNickName(userResponse.getNickUsr()).block();
                if (isValidNic) {
                    user.setNickUsr(userResponse.getNickUsr());
                }
            }
        }
        if (userResponse.getIdSucursal() != null) {
            user.setIdSucursal(userResponse.getIdSucursal());
        }
        if (userResponse.getCargoUsr() != null) {
            user.setCargoUsr(userResponse.getCargoUsr());
        }
        if (userResponse.getHoraInicioUsr() != null) {
            user.setHoraInicioUsr(userResponse.getHoraInicioUsr());
        }
        if (userResponse.getHoraFinUsr() != null) {
            user.setHoraFinUsr(userResponse.getHoraFinUsr());
        }
        user.setEstadoUsr(userResponse.isEstado());
        if (!userResponse.isEstado()) {
            user.setFechaInactivacionUsr(LocalDateTime.now());
        }
        if (userResponse.getImgUsr() != null) {
            user.setImgUsr(userResponse.getImgUsr());
        }
        if (userResponse.getIdCaja() != null) {
            user.setIdCaja(userResponse.getIdCaja());
        }
    }

    /**
     * Método que obtiene un usuario por id
     *
     * @param userId id del usuario
     * @return Mono<TbaUsuario>
     */
    public Mono<TbaUsuario> getUserById(Integer userId) {
        return tbaUsuarioRepository.findById(userId)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + userId)));
    }

    /**
     * Método que obtiene todos los usuarios
     *
     * @param nick           nick del usuario
     * @param status         estado del usuario
     * @param identification identificación del usuario
     * @param name           nombre del usuario
     * @param page           número de página
     * @param limit          número de registros por página
     * @return Mono<Paginacion < TbaUsuario>>
     */
    public Mono<Paginacion<TbaUsuario>> getUsersBySearchParams(String nick, Boolean status, String
            identification, String name, Integer page, Integer limit) {
        try {
            //Validación de parámetros
            boolean searchByNick = nick != null && identification == null && name == null;
            boolean searchByIdentification = nick == null && identification != null && name == null;
            boolean searchByName = nick == null && identification == null && name != null;
            boolean searchByStatus = nick == null && identification == null && name == null;
            if (status == null) {
                status = true;
            }
            Pageable pageable = PageRequest.of(page - 1, limit);
            // Si se busca por nick
            if (searchByNick) {
                Mono<Long> totalCount = tbaUsuarioRepository.countByNickUsrContainingAndEstadoUsr(nick, status);
                Flux<TbaUsuario> usersFlux = tbaUsuarioRepository.findByNickUsrContainingAndEstadoUsr(nick, status, pageable);
                return totalCount.flatMap(count -> usersFlux.collectList().map(data -> new Paginacion<>(data, page, limit, count.intValue())));
            }
            //Si se busca por identificación
            else if (searchByIdentification) {
                Mono<Long> totalCount = getCountByIdentificationAndState(identification, status);
                Flux<TbaUsuario> usuariosFlux = getUsersByIdentificationAndState(identification, status, page, limit);
                return totalCount.flatMap(count -> usuariosFlux.collectList().map(data -> new Paginacion<>(data, page, limit, count.intValue())));
            }
            //Si se busca por nombre
            else if (searchByName) {
                Mono<Long> totalCount = getCountByPersonNameAndStatus(name, status);
                Flux<TbaUsuario> usersFlux = getUsersByPersonNameAndStatus(name, status, page, limit);
                return totalCount.flatMap(count -> usersFlux.collectList().map(data -> new Paginacion<>(data, page, limit, count.intValue())));
            }
            //Si se busca por estado
            else if (searchByStatus) {
                Mono<Long> totalCount = tbaUsuarioRepository.countByEstadoUsr(status);
                Flux<TbaUsuario> usersFlux = tbaUsuarioRepository.findByEstadoUsr(status, pageable);
                return totalCount.flatMap(count -> usersFlux.collectList().map(data -> new Paginacion<>(data, page, limit, count.intValue())));
            }
            // Si no se busca por ningún parámetro se obtienen todos los usuarios
            else {
                Mono<Long> totalCount = tbaUsuarioRepository.count();
                Flux<TbaUsuario> usersFlux = tbaUsuarioRepository.findAllBy(pageable);
                return totalCount.flatMap(count -> usersFlux.collectList().map(data -> new Paginacion<>(data, page, limit, count.intValue())));
            }
        } catch (Exception e) {
            return Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    /**
     * Método que sirve para mappear los data de la tabla TBA_USUARIO
     *
     * @param row fila de la tabla
     * @return TbaUsuario
     */
    private TbaUsuario mapRowToTbaUsuario(Row row) {
        return TbaUsuario.builder()
                .idUsr(row.get("ID_USR", Integer.class))
                .idSucursal(row.get("ID_SUCURSAL", Integer.class))
                .idPersona(row.get("ID_PERSONA", Integer.class))
                .nickUsr(row.get("NICK_USR", String.class))
                .passUsr(row.get("PASS_USR", String.class))
                .cargoUsr(row.get("CARGO_USR", Integer.class))
                .fechaIngresoUsr(row.get("FECHAINGRESO_USR", LocalDateTime.class))
                .fechaInactivacionUsr(row.get("FECHAINACTIVACION_USR", LocalDateTime.class))
                .horaInicioUsr(row.get("HORAINICIO_USR", String.class))
                .horaFinUsr(row.get("HORAFIN_USR", String.class))
                .tiempoUsr(row.get("TIEMPO_USR", Integer.class))
                .idCaja(row.get("id_caja", Integer.class))
                .codigoDesbloqueoUsr(row.get("CODDESBLOQUEO_USR", String.class))
                .imgUsr(row.get("IMG_USR", String.class))
                .estadoUsr(row.get("ESTADO_USR", Boolean.class))
                .build();
    }

    /**
     * Método que sirve para obtener el número de registros cuando se busca por identifación y estado
     *
     * @param identification identificación de la persona
     * @param status         estado del usuario
     * @return Mono<Long>
     */
    private Mono<Long> getCountByIdentificationAndState(String identification, Boolean status) {

        return databaseClient
                .sql("SELECT COUNT(*) FROM TBA_USUARIO " +
                        "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM TBA_PERSONA " +
                        "WHERE IDENTIFICACION_PERSONA LIKE :identification) AND ESTADO_USR = :status")
                .bind("identification", "%" + identification + "%")
                .bind("status", status)
                .map((row, rowMetadata) -> row.get(0, Long.class))
                .one();
    }

    /**
     * Método que sirve para obtener los usuarios cuando se busca por identificación, estado y por paginación
     *
     * @param identification identificación de la persona
     * @param status         estado del usuario
     * @param page           número de página
     * @param limit          número de registros por página
     * @return Flux<TbaUsuario>
     */
    private Flux<TbaUsuario> getUsersByIdentificationAndState(String identification, Boolean status, Integer
            page, Integer limit) {
        Flux<TbaUsuario> usersFlux = databaseClient
                .sql("SELECT * FROM TBA_USUARIO " +
                        "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM TBA_PERSONA " +
                        "WHERE IDENTIFICACION_PERSONA LIKE :identification) AND ESTADO_USR = :status " +
                        "LIMIT :limit OFFSET :offset")
                .bind("identification", "%" + identification + "%")
                .bind("status", status)
                .bind("limit", limit)
                .bind("offset", (page - 1) * limit)
                .map((row, rowMetadata) -> mapRowToTbaUsuario(row))
                .all();
        return usersFlux;
    }

    /**
     * Método que sirve para obtener el número de registros cuando se busca por nombre y estado
     *
     * @param name   nombre de la persona
     * @param status estado del usuario
     * @return Mono<Long>
     */
    private Mono<Long> getCountByPersonNameAndStatus(String name, Boolean status) {
        return databaseClient
                .sql("SELECT COUNT(*) FROM TBA_USUARIO " +
                        "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM TBA_PERSONA " +
                        "WHERE NOMBRE_PERSONA LIKE :name) AND ESTADO_USR = :status")
                .bind("name", "%" + name + "%")
                .bind("status", status)
                .map((row, rowMetadata) -> row.get(0, Long.class))
                .one();
    }

    /**
     * Método que sirve para obtener los usuarios cuando se busca por nombre, estado y por paginación
     *
     * @param name   nombre de la persona
     * @param status estado del usuario
     * @param page   número de página
     * @param limit  número de registros por página
     * @return Flux<TbaUsuario>
     */
    private Flux<TbaUsuario> getUsersByPersonNameAndStatus(String name, Boolean status, Integer page, Integer limit) {
        return databaseClient
                .sql("SELECT * FROM TBA_USUARIO " +
                        "WHERE ID_PERSONA IN (SELECT ID_PERSONA FROM TBA_PERSONA " +
                        "WHERE NOMBRE_PERSONA LIKE :name) AND ESTADO_USR = :status " +
                        "LIMIT :limit OFFSET :offset")
                .bind("name", "%" + name + "%")
                .bind("status", status)
                .bind("limit", limit)
                .bind("offset", (page - 1) * limit)
                .map((row, rowMetadata) -> mapRowToTbaUsuario(row))
                .all();
    }

    public String createNickName(String completeName, Boolean exists, Integer i) {
        String[] name = completeName.split(" ");
        String surname = "";
        String firstName = "";
        String secondName = "";

        if (name.length >= 1) {
            surname = name[0];
        }
        if (name.length >= 3) {
            firstName = name[2];
        }
        if (name.length >= 4) {
            secondName = name[3];
        }

        if (exists && i == 1) {
            return firstName.charAt(0) + secondName.charAt(0) + surname;
        } else if (i == 2) {
            return firstName.substring(0, 1) + secondName.charAt(0) + surname;
        }
        return firstName.charAt(0) + surname;
    }

    public Mono<TbaUsuario> createUser(TbaEmpleado employee, String nickName, Integer personId, Integer chargeId) throws
            NoSuchAlgorithmException {
        return tbaUsuarioRepository.save(TbaUsuario.builder()
                .idPersona(personId)
                .nickUsr(nickName)
                .cargoUsr(chargeId)
                .passUsr(Utils.encriptKey("1234"))
                .idSucursal(employee.getIdSucursal())
                .fechaIngresoUsr(LocalDateTime.now())
                .horaInicioUsr(null)
                .horaFinUsr(null)
                .estadoUsr(true)
                .build());
    }

    public Mono<TbaUsuario> forgotPassword(String nickName) throws NoSuchAlgorithmException {
        Mono<Boolean> exists = tbaUsuarioRepository.existsByNickUsrIgnoreCase(nickName);
        return exists.flatMap(exist -> {
            if (exist) {
                Mono<TbaUsuario> tbaUsuarioMono = tbaUsuarioRepository.findByNickUsrIgnoreCase(nickName);
                return tbaUsuarioMono.flatMap(user -> {
                    try {
                        user.setPassUsr(Utils.encriptKey("1234"));
                        return tbaUsuarioRepository.save(user);
                    } catch (Exception e) {
                        return Mono.error(new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorMessage.PASS_NOT_GENERATE + e.getMessage()));
                    }
                });
            }
            return Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.USER_NOT_FOUND + nickName));
        });
    }

    public Mono<Boolean> validateNickName(String nickName) {
        return tbaUsuarioRepository.existsByNickUsrIgnoreCase(nickName);
    }

    public Mono<Boolean> validateByIdPersona(Integer personId) {
        return tbaUsuarioRepository.existsByIdPersona(personId);
    }

    public Flux<TbaUsuario> getUsersRequestPurchase() {
        return tbaUsuarioRepository.findUsersRequestPurchase(true);
    }

    public Flux<TbaUsuario> getUsersPurchaseOrder() {
        return tbaUsuarioRepository.findUsersPurchaseOrder(true);
    }

    public Flux<TbaUsuario> getSalesUser() {
        return tbaUsuarioCustom.getUserByRoleId(RolEnum.VENTAS.getId());
    }
}
