package com.Alkemy.Disney.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;                    //Anotacion Spring, libreria traida desde spring

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pelicula")
@Getter
@Setter
public class PeliculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;
    private String titulo;

    @Column(name = "fecha_creacion")                                             //Se agrega '@Column' cuando a una columna se la llama con 2 palabras
    @DateTimeFormat(pattern = "dd/MM/yyyy")                                      //Decimos en que formato se guardara la fecha en este atributo
    private LocalDate fechaCreacion;

    private Integer calificacion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)               //'fetch' proporciona una interfaz, para manipular canal HTTP
    @JoinColumn(name = "genero_id", insertable = false, updatable = false)       //Indico que tendra conexion con la entidad 'Genero' mediante 'IdGenero'
    private GeneroEntity genero;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})                //Se crea una nueva tabla, tipo tunel para conectar entidad 'Personaje' y 'Pelicula'
    @JoinTable(name = "personaje_pelicula",                                       //Indico que tendra conexion con la entidad 'Personaje' mediante 'IdPersonaje' lo mismo con 'Pelicula'
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private Set<PersonajeEntity> personajes = new HashSet<>();                    //Indico que sera una coleccion de la entidad 'Personaje', mediante interfaz Set



}
