package com.Alkemy.Disney.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaBasicDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
}
