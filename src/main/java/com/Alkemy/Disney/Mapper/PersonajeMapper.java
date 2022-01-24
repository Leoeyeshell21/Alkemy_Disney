package com.Alkemy.Disney.Mapper;

import com.Alkemy.Disney.DTO.PeliculaDTO;
import com.Alkemy.Disney.DTO.PersonajeBasicDTO;
import com.Alkemy.Disney.DTO.PersonajeDTO;
import com.Alkemy.Disney.Entity.Pelicula;
import com.Alkemy.Disney.Entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {

    @Autowired
    private static PeliculaMapper peliculaMapper;

    public Personaje personajeDTO2Entity(PersonajeDTO dto){
        Personaje personajeEntity = new Personaje();
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(Personaje personajeEntity, boolean loadPeliculas){
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(personajeEntity.getId());
        dto.setImagen(personajeEntity.getImagen());
        dto.setEdad(personajeEntity.getEdad());
        dto.setPeso(personajeEntity.getPeso());
        dto.setHistoria(personajeEntity.getHistoria());
        if(loadPeliculas){
            List<PeliculaDTO> peliculasDTO = peliculaMapper.peliculaEntityList2DTOList(personajeEntity.getPeliculas(), Boolean.FALSE);
            dto.setPeliculas(peliculasDTO);
        }
        return dto;
    }



    public void personajeEntityRefreshValues(Personaje personajeEntity, PersonajeDTO personajeDTO){
        personajeEntity.setImagen(personajeDTO.getImagen());
        personajeEntity.setEdad(personajeDTO.getEdad());
        personajeEntity.setPeso(personajeDTO.getPeso());
        personajeEntity.setHistoria(personajeDTO.getHistoria());
    }

    public Set<Personaje> personajeDTOList2EntityList(List<PersonajeDTO> dtos){
        Set<Personaje> entities = new HashSet<>();
        for(PersonajeDTO dto : dtos){
            entities.add(personajeDTO2Entity(dto));
        }
        return entities;
    }

    public List<PersonajeDTO> personajeEntitySet2DTOList(List<Personaje> entities, Boolean loadPeliculas){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for(Personaje personaje : entities){
            dtos.add(personajeEntity2DTO(personaje, loadPeliculas));
        }
        return dtos;
    }

    public List<PersonajeBasicDTO> personajeEntitySet2BasicDTOList(List<Personaje> entities){
        List<PersonajeBasicDTO> dtos = new ArrayList<>();
        PersonajeBasicDTO personajeBasicDTO;
        for(Personaje personaje : entities){
            personajeBasicDTO = new PersonajeBasicDTO();
            personajeBasicDTO.setId(personaje.getId());
            personajeBasicDTO.setImagen(personaje.getImagen());
            personajeBasicDTO.setNombre(personaje.getNombre());
        }
        return dtos;
    }

}
