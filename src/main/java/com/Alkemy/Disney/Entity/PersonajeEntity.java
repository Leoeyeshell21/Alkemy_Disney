package com.Alkemy.Disney.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personajes")
@Getter
@Setter
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)                              //La relacion va a estar en forma de mapa (Que podremos obtener info de personaje y peliculas)
    private List<PeliculaEntity> peliculas = new ArrayList<>();                                  //Crea una Lista de tipo 'Pelicula' (entidad), se coloca ArrayList ya que es serializable, tranforma los objetos a bytes y maneja de forma mas eficaz

}
