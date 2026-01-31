package erp.base.api.enums

import java.math.BigDecimal

enum class ContratHourEnum(val hours: BigDecimal, val displayName: String) {
    FULL_TIME(BigDecimal.valueOf(8), "Tiempo Completo")
}
