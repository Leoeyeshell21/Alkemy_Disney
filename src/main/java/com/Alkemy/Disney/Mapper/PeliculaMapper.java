package com.Alkemy.Disney.Mapper;

import com.Alkemy.Disney.DTO.PeliculaBasicDTO;
import com.Alkemy.Disney.DTO.PeliculaDTO;
import com.Alkemy.Disney.DTO.PersonajeDTO;
import com.Alkemy.Disney.Entity.Pelicula;
import com.Alkemy.Disney.Entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;

    public Pelicula peliculaDTO2Entity(PeliculaDTO dto){
        Pelicula peliculaEntity = new Pelicula();
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setFechaCreacion(string2LocalDate(dto.getFechaCreacion()));
        return peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2DTO(Pelicula peliculaEntity , boolean loadPersonajes){
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(peliculaEntity.getId());
        dto.setImagen(peliculaEntity.getImagen());
        dto.setCalificacion(peliculaEntity.getCalificacion());
        dto.setTitulo(peliculaEntity.getTitulo());
        dto.setGeneroId(peliculaEntity.getGenero());
        dto.setFechaCreacion(peliculaEntity.getFechaCreacion().toString());
        if(loadPersonajes){
            List<PersonajeDTO> personajesDTOS = personajeMapper.personajeEntitySet2DTOList(peliculaEntity.getPersonajes() , Boolean.FALSE);
            dto.setPersonajes(personajesDTOS);
        }
        return dto;
    }

    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate,formatter);
        return date;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<Pelicula> entities, boolean loadPersonajes){
        List<PeliculaDTO> dtos = new ArrayList<>();
        for(Pelicula peliculaEntity : entities){
            dtos.add(peliculaEntity2DTO(peliculaEntity,loadPersonajes));
        }
        return dtos;
    }

    public PeliculaBasicDTO entity2BasicDTO(Pelicula peliculaEntity) {
        PeliculaBasicDTO dto = new PeliculaBasicDTO();
        dto.setImagen(peliculaEntity.getImagen());
        dto.setTitulo(peliculaEntity.getTitulo());
        LocalDate fecha = peliculaEntity.getFechaCreacion();
        String formatDate = fecha.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dto.setFechaCreacion(formatDate);
        return dto;
    }

    public List<PeliculaBasicDTO> entityList2BasicDTO(List<Pelicula> entities) {
        List<PeliculaBasicDTO> peliculas = new ArrayList<>();
        for (Pelicula peliculaEntity : entities) {
            peliculas.add(this.entity2BasicDTO(peliculaEntity));
        }
        return peliculas;
    }

    public List<Pelicula> peliculaDTOList2EntityList(List<PeliculaDTO> dtos){
        List<Pelicula> entities = new ArrayList<>();
        for(PeliculaDTO dto : dtos){
            entities.add(peliculaDTO2Entity(dto));
        }
        return entities;
    }

    public void peliculaEntityRefreshValues(Pelicula peliculaEntity, PeliculaDTO peliculaDTO){
        peliculaEntity.setImagen(peliculaDTO.getImagen());
        peliculaEntity.setTitulo(peliculaDTO.getTitulo());
        peliculaEntity.setFechaCreacion(
                this.string2LocalDate(peliculaDTO.getFechaCreacion())
        );

    }
}
