package erp.base.api.utils;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.exception.CustomException;
import erp.base.api.models.TipoDocumento;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Clase con métodos útiles
 */
public class Utils {


    /**
     * Convierte un Date a LocalDateTime
     *
     * @param date Date a convertir
     * @return LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        return localDateTime;
    }

    public static BigDecimal calcularCostoHora(BigDecimal sueldo, BigDecimal horas) {
        return sueldo.divide(BigDecimal.valueOf(30), 6, RoundingMode.UP).divide(horas, 6, RoundingMode.UP);
    }

    public static Boolean ValidadorCedula(String identificacion) {
        try {
            ValidationCI.validateInitial(identificacion, 10);
            ValidationCI.validateCodProvince(identificacion.substring(0, 2));
            ValidationCI.validateThirdDigit(String.valueOf(identificacion.charAt(2)), TipoDocumento.getTipoCedula());
        } catch (Exception e) {
            new CustomException(HttpStatus.EXPECTATION_FAILED, ErrorMessage.CI_ERROR);
            return false;
        }
        return true;
    }

    /*Método para validar si contiene números*/

    public static boolean validateNumbers(String param) {
        return param.matches("[0-9]*");
    }

    public static Integer calcularEdad(LocalDate fechaNacimiento) throws ParseException {
        LocalDate fechaActual = LocalDate.now();

        Period period = Period.between(fechaNacimiento, fechaActual);
        return period.getYears();
    }

    public static String encriptKey(String clave) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(clave.getBytes());
        StringBuilder hexHash = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexHash.append('0');
            hexHash.append(hex);
        }
        return hexHash.toString();
    }

    public static ZonedDateTime getZonedDateTime() {
        ZonedDateTime utcDateTime = ZonedDateTime.now();
        ZoneId ecuadorZone = ZoneId.of("America/Guayaquil");
        return utcDateTime.withZoneSameInstant(ecuadorZone);
    }

    public static ZonedDateTime setDateTimeEcuadorFromDate(Date utcDate) {
        ZoneId ecuadorZone = ZoneId.of("America/Guayaquil");
        return ZonedDateTime.ofInstant(utcDate.toInstant(), ecuadorZone);
    }

    public static ZonedDateTime currentZonedDateTimeEcuador() {
        ZoneId ecuadorZone = ZoneId.of("America/Guayaquil");
        return ZonedDateTime.now(ecuadorZone);
    }

    public static String getClientIp(ServerRequest request) {
        String origin = request.headers().firstHeader("Origin");
        if (origin != null) {
            // If the request has been forwarded, the first IP in the list is the original client IP
            return origin.split(",")[0].trim();
        }

        // If X-Forwarded-For header is not present, fall back to the remote address
        return request.remoteAddress().get().getHostString();
    }

    public static String getClientHostname(ServerRequest request) {
        try {
            InetAddress inetAddress = InetAddress.getByName(request.remoteAddress().get().getHostString());
            return inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Unknown Host";
        }
    }

    public static String convertListToCommaSeparatedString(List<Integer> integerList) {
        StringJoiner joiner = new StringJoiner(",");
        for (Integer number : integerList) {
            joiner.add(number.toString());
        }
        return joiner.toString();
    }

    /**
     *
     * @param byCurrentMonth Parámetro que indica si se debe filtrar por el mes actual
     * @param endDateParam Fecha de fin de la búsqueda
     * @return
     */
    public static ZonedDateTime getEndDateForPagination(boolean byCurrentMonth, ZonedDateTime endDateParam) {
        if (byCurrentMonth || endDateParam == null)
            return setDateTimeEndDay(currentZonedDateTimeEcuador());
        else
            return setDateTimeEndDay(endDateParam);
    }

    /**
     * Método que sirve para setear la fecha de inicio del día a una fecha dada en ZonedDateTime (hora 00:00:00)
     * @param date Fecha a la que se le seteará la hora de inicio del día
     * @return Fecha con la hora de inicio del día
     */
    public static ZonedDateTime setDateTimeStartDay(ZonedDateTime date) {
        return date.withHour(0).withMinute(0).withSecond(0).withNano(0);
    }

    /**
     * Método que sirve para
     * @param date
     * @return fecha converti
     */
    public static ZonedDateTime setDateTimeEndDay(ZonedDateTime date) {
        return date.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
    }

    public static ZonedDateTime getStartDateForPagination(boolean byCurrentMonth, ZonedDateTime startDateParam) {
        if (byCurrentMonth)
            return setDateTimeStartDay(currentZonedDateTimeEcuador().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0));
        return setDateTimeStartDay(Objects.requireNonNullElseGet(startDateParam, () -> ZonedDateTime.of(1900, 1, 1, 0, 0, 0, 0, ZoneId.of("America/Guayaquil"))));
    }

    public static ZonedDateTime parseStringToZonedDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate.atStartOfDay(ZoneId.of("America/Guayaquil"));
    }

}
