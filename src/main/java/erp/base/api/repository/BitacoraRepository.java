package erp.base.api.repository;

import erp.base.api.dto.BitacoraDto;
import erp.base.api.models.Bitacora;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BitacoraRepository extends ReactiveCrudRepository<Bitacora, Integer> {

    @Query("SELECT BITACORA.ID_BITACORA                AS id," +
            " BITACORA.ID_USR                     AS idusuario," +
            " USUARIO.NICK_USR                    AS nombreusuario," +
            " BITACORA.IDENTIFICADOR_BITACORA     AS identificador," +
            " BITACORA.TABLE_BITACORA             AS tabla," +
            " BITACORA.CAMPO_BITACORA             AS campo," +
            " BITACORA.OLDVAL_BITACORA            AS oldvalue," +
            " BITACORA.NEWVAL_BITACORA            AS newvalue," +
            " BITACORA.FECHA_BITACORA             AS fecha," +
            " BITACORA.IPDISPOSITIVO_BITACORA     AS ip," +
            " BITACORA.NOMBREDISPOSITIVO_BITACORA AS dispositivo" +
            " FROM BITACORA" +
            " JOIN TBA_USUARIO USUARIO ON USUARIO.ID_USR = BITACORA.ID_USR" +
            " WHERE IDENTIFICADOR_BITACORA = :id AND TABLE_BITACORA = :table")
    Flux<BitacoraDto> findAllByIdAndTable(Integer id, String table);
}
