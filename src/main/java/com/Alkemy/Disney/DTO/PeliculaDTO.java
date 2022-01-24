package com.Alkemy.Disney.DTO;

import com.Alkemy.Disney.Entity.Genero;
import com.Alkemy.Disney.Entity.Personaje;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {
    private Long Id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private Integer calificacion;
    private List<PersonajeDTO> personajes;
    private Genero generoId;
}
