package com.Alkemy.Disney.Entity;
                                                            //Import agrega paquetes que necesitaremos desde "librerias"
import lombok.Getter;                                       //Dependencia Lombok (evita agregar getters y setters)
import lombok.Setter;

import javax.persistence.*;                                 //dependencia JPA (@)

@Entity                                                     //indico que sera una entidad
@Table(name = "genero")                                     //indico nombre a la tabla
@Getter                                                     //indico que se obtendra resultados de la entidad
@Setter                                                     //indico que ingresare resultados a la entidad

public class GeneroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)      //genero por secuencia los ids (1,2,3,...)
    private Long id;

    private String imagen;
    private String nombre;

}
