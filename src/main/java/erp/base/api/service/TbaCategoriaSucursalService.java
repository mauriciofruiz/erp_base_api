package erp.base.api.service;

import erp.base.api.constants.ErrorMessage;
import erp.base.api.dto.TbaCategoriaSucursalDto;
import erp.base.api.models.TbaCategoriaSucursal;
import erp.base.api.exception.CustomException;
import erp.base.api.repository.TbaCategoriaSucursalRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TbaCategoriaSucursalService {

    private final TbaCategoriaSucursalRepository tbaCategoriaSucursalRepository;


    /**
     * Método para crear una categoria de sucursal
     * @param tbaCategoriaSucursalDto Objeto con los datos de la categoria de sucursal
     * @return Objeto con los datos de la categoria de sucursal creada
     */
    public Mono<TbaCategoriaSucursal> creatBranchCategory(TbaCategoriaSucursalDto tbaCategoriaSucursalDto){
        Mono<Boolean> existsBranchCategory = tbaCategoriaSucursalRepository.existsById(tbaCategoriaSucursalDto.getIdCategoriaSucursal());
         return existsBranchCategory.flatMap(existsBranchCategoryId -> existsBranchCategoryId ?
                 Mono.error(new CustomException(HttpStatus.CONFLICT, ErrorMessage.BRANCH_CATEGORY_ID_EXISTS + tbaCategoriaSucursalDto.getIdCategoriaSucursal()))
                 : tbaCategoriaSucursalRepository.insert(
                         tbaCategoriaSucursalDto.getIdCategoriaSucursal(),
                         tbaCategoriaSucursalDto.getNombreCategoriaSucursal(),
                         tbaCategoriaSucursalDto.getDescripcionCategoriaSucursal()
                 )
                 .then(tbaCategoriaSucursalRepository.findById(tbaCategoriaSucursalDto.getIdCategoriaSucursal())));

    }

    /**
     * Método para actualizar una categoria de sucursal
     * @param id Id de la categoria de sucursal a actualizar
     * @param tbaCategoriaSucursalDto Los objetos de la categoria sucursal a actualizar
     * @return  TbaCategoriaSucursal
     */

    public  Mono<TbaCategoriaSucursal> updateBranchCategory(String id, TbaCategoriaSucursalDto tbaCategoriaSucursalDto){
        Mono<Boolean> existsBranchCategory = tbaCategoriaSucursalRepository.existsById(id);
        return existsBranchCategory.flatMap( branchCategory -> branchCategory ?
                tbaCategoriaSucursalRepository.save(
                       TbaCategoriaSucursal.builder()
                               .idCategoriaSucursal(id)
                               .nombreCategoriaSucursal(tbaCategoriaSucursalDto.getNombreCategoriaSucursal())
                               .descripcionCategoriaSucursal(tbaCategoriaSucursalDto.getDescripcionCategoriaSucursal())
                               .build())
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_CATEGORY_NOT_FOUND + id
        )));
    }

    /**
     * Método para obtener una categoria de sucursal por su id
     * @param id Id de la categoria de sucursal a obtener
     * @return Objeto con los datos de la categoria de sucursal
     */

    public Mono<TbaCategoriaSucursal> getBranchCategoryId(String id){
        return tbaCategoriaSucursalRepository.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_CATEGORY_NOT_FOUND + id)));
    }

    /***
     * Método para encontrar categoria sucursal por su nombre
     * @param categoryName de la sucrusal a buscar
     * @return Objeto de TbaCategoriaSucursal
     */

    public Flux<TbaCategoriaSucursal> getBranchCategoryName(String categoryName){
        Boolean isSearchBranchName = categoryName == null;
        return  isSearchBranchName ?  tbaCategoriaSucursalRepository.findAll()
               : tbaCategoriaSucursalRepository.findByNombreCategoriaSucursal(categoryName)
                .switchIfEmpty(Flux.error(new CustomException(HttpStatus.NOT_FOUND, ErrorMessage.BRANCH_CATEGORY_NOT_FOUND + categoryName)));


    }

}
