package com.Alkemy.Disney.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PeliculaFiltersDTO {
    private String titulo;
    private Set<Long> generos;
    private String order;

    public PeliculaFiltersDTO(String titulo, Set<Long> generos,String order){
        this.titulo = titulo;
        this.generos = generos;
        this.order = order;
    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return order.compareToIgnoreCase("DESC") == 0;
    }
}
