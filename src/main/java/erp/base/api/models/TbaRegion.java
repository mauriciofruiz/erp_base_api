package erp.base.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("TBA_REGION")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TbaRegion {
    @Id
    @Column("ID_REGION")
    @JsonProperty("regionId")
    private Integer idRegion;

    @Column("NOMBRE_REGION")
    @JsonProperty("regionName")
    private String nombreRegion;
}
