package erp.base.api.utils

import erp.base.api.constants.ErrorMessage
import erp.base.api.exception.CustomException
import erp.base.api.models.TipoDocumento
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest
import java.math.BigDecimal
import java.math.RoundingMode
import java.net.InetAddress
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

/**
 * Clase con métodos útiles
 */
object Utils {

    @JvmStatic
    fun dateToLocalDateTime(date: Date): LocalDateTime {
        val instant = date.toInstant()
        val zonedDateTime = instant.atZone(ZoneId.systemDefault())
        return zonedDateTime.toLocalDateTime()
    }

    @JvmStatic
    fun calcularCostoHora(sueldo: BigDecimal, horas: BigDecimal): BigDecimal =
        sueldo.divide(BigDecimal.valueOf(30), 6, RoundingMode.UP).divide(horas, 6, RoundingMode.UP)

    @JvmStatic
    fun ValidadorCedula(identificacion: String): Boolean {
        return try {
            ValidationCI.validateInitial(identificacion, 10)
            ValidationCI.validateCodProvince(identificacion.substring(0, 2))
            ValidationCI.validateThirdDigit(identificacion[2].toString(), TipoDocumento.getTipoCedula())
            true
        } catch (e: Exception) {
            CustomException(HttpStatus.EXPECTATION_FAILED, ErrorMessage.CI_ERROR)
            false
        }
    }

    @JvmStatic
    fun validateNumbers(param: String): Boolean = param.matches(Regex("[0-9]*"))

    @JvmStatic
    fun calcularEdad(fechaNacimiento: LocalDate): Int {
        val fechaActual = LocalDate.now()
        val period = Period.between(fechaNacimiento, fechaActual)
        return period.years
    }

    @JvmStatic
    fun encriptKey(clave: String): String {
        val messageDigest = java.security.MessageDigest.getInstance("SHA-256")
        val hash = messageDigest.digest(clave.toByteArray())
        return hash.joinToString("") { "%02x".format(it) }
    }

    @JvmStatic
    fun getZonedDateTime(): ZonedDateTime {
        val utcDateTime = ZonedDateTime.now()
        val ecuadorZone = ZoneId.of("America/Guayaquil")
        return utcDateTime.withZoneSameInstant(ecuadorZone)
    }

    @JvmStatic
    fun setDateTimeEcuadorFromDate(utcDate: Date): ZonedDateTime {
        val ecuadorZone = ZoneId.of("America/Guayaquil")
        return ZonedDateTime.ofInstant(utcDate.toInstant(), ecuadorZone)
    }

    @JvmStatic
    fun currentZonedDateTimeEcuador(): ZonedDateTime {
        val ecuadorZone = ZoneId.of("America/Guayaquil")
        return ZonedDateTime.now(ecuadorZone)
    }

    @JvmStatic
    fun getClientIp(request: ServerRequest): String {
        val origin = request.headers().firstHeader("Origin")
        if (origin != null) {
            return origin.split(",")[0].trim()
        }
        return request.remoteAddress().get().hostString ?: ""
    }

    @JvmStatic
    fun getClientHostname(request: ServerRequest): String {
        return try {
            val inetAddress = InetAddress.getByName(request.remoteAddress().get().hostString)
            inetAddress.hostName
        } catch (e: Exception) {
            e.printStackTrace()
            "Unknown Host"
        }
    }

    @JvmStatic
    fun convertListToCommaSeparatedString(integerList: List<Int>): String =
        integerList.joinToString(",")

    @JvmStatic
    fun getEndDateForPagination(byCurrentMonth: Boolean, endDateParam: ZonedDateTime?): ZonedDateTime {
        return if (byCurrentMonth || endDateParam == null) setDateTimeEndDay(currentZonedDateTimeEcuador())
        else setDateTimeEndDay(endDateParam)
    }

    @JvmStatic
    fun setDateTimeStartDay(date: ZonedDateTime): ZonedDateTime =
        date.withHour(0).withMinute(0).withSecond(0).withNano(0)

    @JvmStatic
    fun setDateTimeEndDay(date: ZonedDateTime): ZonedDateTime =
        date.withHour(23).withMinute(59).withSecond(59).withNano(999999999)

    @JvmStatic
    fun getStartDateForPagination(byCurrentMonth: Boolean, startDateParam: ZonedDateTime?): ZonedDateTime {
        return if (byCurrentMonth) {
            setDateTimeStartDay(currentZonedDateTimeEcuador().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0))
        } else {
            setDateTimeStartDay(startDateParam ?: ZonedDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneId.of("America/Guayaquil")))
        }
    }

    @JvmStatic
    fun parseStringToZonedDateTime(dateString: String): ZonedDateTime {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val localDate = LocalDate.parse(dateString, formatter)
        return localDate.atStartOfDay(ZoneId.of("America/Guayaquil"))
    }
}
