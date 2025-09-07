package erp.base.api.security.jwt;

import erp.base.api.models.Sesion;
import erp.base.api.repository.SesionRepository;
import erp.base.api.security.entity.User;
import erp.base.api.utils.Utils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;


/**
 * Esta clase se encarga de generar el token JWT y de validar su firma.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    private final SesionRepository sesionRepository;

    @Value("${jwt.secret}")
    private String secret;

    private Boolean isWithinSchedule;

    private Integer timeRemainingMinutes;

    /**
     * Genera un token JWT con el nombre de usuario y los roles del usuario.
     * @param user detalles del usuario.
     * @return token JWT.
     */
    public String generateToken(User user) {

        Date expirationDate = getExpirationDateFromToken(user);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", user.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(getKey(secret))
                .compact();

        Mono<Sesion> sesionMono = sesionRepository.findByIdUsr(user.getUserId());
        sesionMono.flatMap(sesion ->
                sesionRepository.save(Sesion.builder()
                        .idSesion(sesion.getIdSesion())
                        .idUsr(user.getUserId())
                        .token(token)
                        .estado(isWithinSchedule)
                        .fechaCreacion(Utils.getZonedDateTime())
                        .fechaExpiracion(Utils.setDateTimeEcuadorFromDate(expirationDate))
                        .duracionMinutos(timeRemainingMinutes)
                        .build())
                ).switchIfEmpty(sesionRepository.save(Sesion.builder()
                .idUsr(user.getUserId())
                .token(token)
                .estado(isWithinSchedule)
                .fechaCreacion(Utils.getZonedDateTime())
                .fechaExpiracion(Utils.setDateTimeEcuadorFromDate(expirationDate))
                .duracionMinutos(timeRemainingMinutes)
                .build())).subscribe();
        return token;
    }

    /**
     * Obtiene los claims del token JWT.
     * @param token token JWT.
     * @return  claims del token JWT.
     */
    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
    }


    /**
     * Obtiene la clave secreta a partir de un string.
     * @param secret clave secreta.
     * @return clave secreta.
     */
    private Key getKey(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    /**
     * Obtiene la fecha de expiración del token JWT.
     * @param user detalles del usuario.
     * @return fecha de expiración.
     */
    private Date getExpirationDateFromToken(User user) {
        String startTimeString = user.getHoraInicioUsr() != null ? user.getHoraInicioUsr() : "08:00"; // Hora de inicio permitida
        String endTimeString = user.getHoraFinUsr() != null ? user.getHoraFinUsr() : "18:00"; // Hora de fin permitida
        int sessionTime = user.getTiempoUsr() != null ? user.getTiempoUsr() : 540; // Tiempo del usuario en minutos

        // Obtener la hora actual del sistema en la zona "America/Guayaquil"
        ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("America/Guayaquil"));

        // Convertir las horas de inicio y fin almacenadas en Strings a ZonedDateTime
        ZonedDateTime startTime = ZonedDateTime.of(currentTime.toLocalDate(), LocalTime.parse(startTimeString), ZoneId.of("America/Guayaquil"));
        ZonedDateTime endTime = ZonedDateTime.of(currentTime.toLocalDate(), LocalTime.parse(endTimeString), ZoneId.of("America/Guayaquil"));

        // Verificar si la hora actual está dentro del horario permitido
        isWithinSchedule = currentTime.isAfter(startTime) && currentTime.isBefore(endTime);

        if (Boolean.TRUE.equals(isWithinSchedule)) {
            // Calcular la diferencia de tiempo hasta la hora fin
            long diferenciaMinutos = currentTime.until(endTime, ChronoUnit.MINUTES);

            // Calcular el tiempo restante disponible considerando el tiempo del usuario
            timeRemainingMinutes = Math.toIntExact(Math.min(diferenciaMinutos, sessionTime));
        }
        else{
            throw new JwtException("El usuario no tiene acceso en este horario");
        }
        return new Date(new Date().getTime() + timeRemainingMinutes * 60000L);
    }
}
