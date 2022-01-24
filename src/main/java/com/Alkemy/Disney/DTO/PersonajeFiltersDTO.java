package com.Alkemy.Disney.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {
    private String nombre;
    private Integer edad;
    private Set<Long> peliculas;
    private String order;

    public PersonajeFiltersDTO(String nombre,Integer edad, Set<Long>peliculas,String order){
        this.nombre = nombre;
        this.edad = edad;
        this.peliculas = peliculas;
        this.order = order;
    }
    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return order.compareToIgnoreCase("DESC") == 0;
    }

}
