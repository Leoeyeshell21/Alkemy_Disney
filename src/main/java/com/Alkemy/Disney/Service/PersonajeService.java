package com.Alkemy.Disney.Service;

import com.Alkemy.Disney.DTO.PersonajeBasicDTO;
import com.Alkemy.Disney.DTO.PersonajeDTO;
import com.Alkemy.Disney.Entity.Personaje;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface PersonajeService {

    Personaje getById(Long id);

    PersonajeDTO getDetailsById(Long id);

    List<PersonajeBasicDTO> getAll();

    List<PersonajeDTO> getByFilters(String nombre, Integer edad, Set<Long> peliculas,String order);

    PersonajeDTO save(PersonajeDTO personajeDTO);

    PersonajeDTO update(Long id, PersonajeDTO personaje);

    void addPelicula(Long id,Long idPelicula);

    void removePelicula(Long id, Long idPelicula);

    void delete(Long id);
}
