package com.sicon.tba.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Clase que contiene la información de paginación
 * @param <T> tipo de objeto
 */
@Data
public class Paginacion<T> {
    private List<T> list;
    private Integer page;
    private Integer limit;
    private Integer totalRecords;
    private Integer totalPages;

    public Paginacion(List<T> list, Integer page, Integer limit, Integer totalRecords) {
        this.list = list;
        this.page = page;
        this.limit = limit;
        this.totalRecords = totalRecords;
        this.totalPages = (int) Math.ceil((double) totalRecords / limit);
    }


}
