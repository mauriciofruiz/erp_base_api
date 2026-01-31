package erp.base.api.security.jwt

import erp.base.api.models.Sesion
import erp.base.api.repository.SesionRepository
import erp.base.api.security.entity.User
import erp.base.api.utils.Utils
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.security.Key
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.Date

@Component
class JwtProvider(
    private val sesionRepository: SesionRepository,
) {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    private var isWithinSchedule: Boolean? = null
    private var timeRemainingMinutes: Int? = null

    fun generateToken(user: User): String {
        val expirationDate = getExpirationDateFromToken(user)
        val token = Jwts.builder()
            .setSubject(user.username)
            .claim("roles", user.authorities)
            .setIssuedAt(Date())
            .setExpiration(expirationDate)
            .signWith(getKey(secret))
            .compact()

        sesionRepository.findByIdUsr(user.idUsr!!)
            .flatMap { sesion ->
                sesionRepository.save(
                    Sesion(
                        idSesion = sesion.idSesion,
                        idUsr = user.idUsr,
                        token = token,
                        fechaCreacion = Utils.getZonedDateTime(),
                        fechaExpiracion = Utils.setDateTimeEcuadorFromDate(expirationDate),
                        estado = isWithinSchedule,
                        duracionMinutos = timeRemainingMinutes,
                    )
                )
            }
            .switchIfEmpty(
                sesionRepository.save(
                    Sesion(
                        idSesion = null,
                        idUsr = user.idUsr,
                        token = token,
                        fechaCreacion = Utils.getZonedDateTime(),
                        fechaExpiracion = Utils.setDateTimeEcuadorFromDate(expirationDate),
                        estado = isWithinSchedule,
                        duracionMinutos = timeRemainingMinutes,
                    )
                )
            )
            .subscribe()
        return token
    }

    fun getClaims(token: String) =
        Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).body

    private fun getKey(secretKey: String): Key {
        val secretBytes = Decoders.BASE64URL.decode(secretKey)
        return Keys.hmacShaKeyFor(secretBytes)
    }

    private fun getExpirationDateFromToken(user: User): Date {
        val startTimeString = user.horaInicioUsr ?: "08:00"
        val endTimeString = user.horaFinUsr ?: "18:00"
        val sessionTime = user.tiempoUsr ?: 540

        val currentTime = ZonedDateTime.now(ZoneId.of("America/Guayaquil"))
        val startTime = ZonedDateTime.of(
            currentTime.toLocalDate(),
            LocalTime.parse(startTimeString),
            ZoneId.of("America/Guayaquil")
        )
        val endTime = ZonedDateTime.of(
            currentTime.toLocalDate(),
            LocalTime.parse(endTimeString),
            ZoneId.of("America/Guayaquil")
        )

        isWithinSchedule = currentTime.isAfter(startTime) && currentTime.isBefore(endTime)
        if (isWithinSchedule == true) {
            val diferenciaMinutos = currentTime.until(endTime, ChronoUnit.MINUTES)
            timeRemainingMinutes = Math.min(diferenciaMinutos, sessionTime.toLong()).toInt()
        } else {
            throw JwtException("El usuario no tiene acceso en este horario")
        }
        return Date(Date().time + timeRemainingMinutes!! * 60000L)
    }
}
