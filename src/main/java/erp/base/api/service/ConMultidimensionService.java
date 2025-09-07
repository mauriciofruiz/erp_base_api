package erp.base.api.service;

import erp.base.api.enums.Multidimensional;
import erp.base.api.models.ConMultidimension;
import erp.base.api.repository.cont.ConMultidimensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConMultidimensionService {

    private final ConMultidimensionRepository conMultidimensionRepository;


    public Mono<ConMultidimension> createMultidimensionEmployee(Integer idEmployee) {
        return conMultidimensionRepository.save(ConMultidimension.builder()
                        .tipoMultidimension(Multidimensional.EMPLOYEE.getName())
                        .valorMultidimension(idEmployee)
                .build());
    }
}
