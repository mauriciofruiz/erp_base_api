package com.sicon.tba.security.service;

import com.sicon.tba.constants.ErrorMessage;
import com.sicon.tba.dto.RolResponseDto;
import com.sicon.tba.dto.TbaMenuUsuarioResponseDto;
import com.sicon.tba.models.*;
import com.sicon.tba.exception.CustomException;
import com.sicon.tba.repository.*;
import com.sicon.tba.security.dto.LoginDto;
import com.sicon.tba.security.dto.LoginResponseDto;
import com.sicon.tba.security.jwt.JwtProvider;
import com.sicon.tba.security.repository.UserRepository;
import com.sicon.tba.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Clase que maneja la lógica de negocio de los usuarios
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final TbaMenuUsuarioRepository tbaMenuUsuarioRepository;

    private final TbaPersonaRepository personaRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final SesionRepository sesionRepository;

    private final AccesoDocumentoRepository accesoDocumentoRepository;

    private final RolRepository rolRepository;

    /**
     * Metodo que maneja la logica de negocio del login
     *
     * @param dto dto
     * @return LoginResponseDto
     */
    public Mono<LoginResponseDto> login(LoginDto dto) {
        return userRepository.findByNickUsrAndEstadoUsr(dto.getNick().toUpperCase(), Boolean.TRUE)
                .filter(user -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .flatMap(user -> sesionRepository.existsSesionByIdUsrAndEstado(user.getUserId(), Boolean.TRUE)
                        .flatMap(exists -> {
                            if (exists) {
                                Mono<Sesion> sesionMono = sesionRepository.findByIdUsrAndEstado(user.getUserId(), Boolean.TRUE);
                                return sesionMono.flatMap(sesion -> {
                                            boolean expiratedSession = sesion.getFechaExpiracion().isBefore(Utils.currentZonedDateTimeEcuador());
                                            if (expiratedSession) {
                                                return this.logout(sesion.getToken())
                                                        .then(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Sesión expirada, ingrese nuevamente")));

                                            } else {
                                                return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Sesión activa en otro dispositivo"));
                                            }
                                        }
                                );
                            } else {
                                Mono<List<TbaMenuUsuarioResponseDto>> menuUserResponseMono = tbaMenuUsuarioRepository.findMenusByUserId(user.getUserId())
                                        .collectList()
                                        .onErrorResume(throwable -> {
                                            log.error("Error occurred while fetching menu for user {}", user.getUserId(), throwable);
                                            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Error al obtener el menú"));
                                        });
                                Mono<TbaPersona> personMono = personaRepository.findById(user.getPersonId());
                                Mono<List<RolResponseDto>> rolesMono = rolRepository.getActiveRolesByUser(user.getUserId())
                                        .collectList()
                                        .onErrorResume(throwable -> {
                                            log.error("Error occurred while fetching roles for user {}", user.getUserId(), throwable);
                                            return Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Error al obtener los roles"));
                                        });
                                return Mono.zip(menuUserResponseMono, personMono, rolesMono)
                                        .flatMap(tuple -> {
                                            List<TbaMenuUsuarioResponseDto> menuUserResponseList = tuple.getT1();
                                            TbaPersona persona = tuple.getT2();
                                            return Mono.just(LoginResponseDto.builder()
                                                    .userId(user.getUserId())
                                                    .userName(persona.getNombrePersona())
                                                    .userNick(user.getUsername())
                                                    .password(user.getPassword())
                                                    .token(jwtProvider.generateToken(user))
                                                    .photo(user.getImgUsr())
                                                    .position(user.getCargoUsr())
                                                    .menuList(menuUserResponseList)
                                                    .cashId(user.getIdCaja())
                                                    .branchId(user.getIdSucursal())
                                                    .roles(tuple.getT3())
                                                    .build());
                                        });
                            }
                        }))
                        .

                switchIfEmpty(Mono.error(new CustomException(HttpStatus.BAD_REQUEST, "Usuario o contraseña incorrectos")));
//                .onErrorMap(throwable -> {
//                    log.error("Error occurred during login process", throwable);
//                    return new CustomException(HttpStatus.BAD_REQUEST, "Error en la autenticación");
//                });
    }

    public Mono<String> logout(String token) {
        return sesionRepository.findByToken(token)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.SESSION_NOT_FOUND)))
                .flatMap(sesion -> userRepository.findById(sesion.getIdUsr())
                        .flatMap(usuario -> accesoDocumentoRepository.findByNameUsuario(usuario.getNickUsr())
                                .collectList()
                                .flatMap(accesoDocumentos -> {
                                    if (accesoDocumentos != null && !accesoDocumentos.isEmpty()) {
                                        return accesoDocumentoRepository.deleteAll(accesoDocumentos)
                                                .then(Mono.fromRunnable(() -> sesion.setEstado(Boolean.FALSE)))
                                                .then(sesionRepository.save(sesion))
                                                .thenReturn("Logout exitoso");
                                    } else {
                                        sesion.setEstado(Boolean.FALSE);
                                        return sesionRepository.save(sesion)
                                                .thenReturn("Logout exitoso");
                                    }
                                })
                        )
                )
                .onErrorResume(throwable -> Mono.just("Logout Fallido"));
    }


}
