package com.Alkemy.Disney.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonajeDTO {
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    private List<PeliculaDTO> peliculas;

}
