package com.Alkemy.Disney.Service;


import com.Alkemy.Disney.DTO.PeliculaBasicDTO;
import com.Alkemy.Disney.DTO.PeliculaDTO;
import com.Alkemy.Disney.Entity.Pelicula;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO pelicula);

    List<PeliculaDTO> getAllPeliculas();

    List<PeliculaBasicDTO> getBasicList();

    PeliculaDTO update(Long id, PeliculaDTO peliculaDTO);

    void delete(Long id);

    void addPersonaje(Long id, Long personajeId);

    Pelicula getById(Long id);

    PeliculaDTO getByDetails(Long id);

    void addGenero(Long id, Long generoId);

    List<PeliculaDTO> getByFilters(String nombre, Set<Long> generos , String order);

}
