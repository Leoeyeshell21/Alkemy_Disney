package com.Alkemy.Disney.Service.Implements;

import com.Alkemy.Disney.DTO.*;
import com.Alkemy.Disney.Entity.Pelicula;
import com.Alkemy.Disney.Entity.Personaje;
import com.Alkemy.Disney.Exception.ParamNotFound;
import com.Alkemy.Disney.Mapper.PeliculaMapper;
import com.Alkemy.Disney.Repository.PeliculaRepository;
import com.Alkemy.Disney.Repository.Specification.PeliculaSpecification;
import com.Alkemy.Disney.Service.PeliculaService;
import com.Alkemy.Disney.Service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PeliculaServiceImp implements PeliculaService  {

    @Autowired
    private PersonajeService personajeServicio;
    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PeliculaRepository peliculaRepositorio;
    @Autowired
    private PeliculaSpecification peliculaSpecification;

    public PeliculaDTO save(PeliculaDTO dto) {
        Pelicula peliculaEntity = peliculaMapper.peliculaDTO2Entity(dto);
        Pelicula peliculaSaved = peliculaRepositorio.save(peliculaEntity);
        PeliculaDTO peliculaResult = peliculaMapper.peliculaEntity2DTO(peliculaSaved, false);
        return peliculaResult;
    }

    public List<PeliculaDTO> getAllPeliculas() {
        List<Pelicula> entities = peliculaRepositorio.findAll();
        List<PeliculaDTO> peliculasResult = peliculaMapper.peliculaEntityList2DTOList(entities,true);
        return peliculasResult;
    }

    public void delete(Long id) {
        peliculaRepositorio.deleteById(id);
    }

    public List<PeliculaBasicDTO> getBasicList() {
        List<Pelicula> peliculas = peliculaRepositorio.findAll();
        List<PeliculaBasicDTO> dtos = peliculaMapper.entityList2BasicDTO(peliculas);
        return dtos;
    }

    public PeliculaDTO update(Long id,PeliculaDTO dto){
        Optional<Pelicula> peliculaEntity = peliculaRepositorio.findById(id);
        if(!peliculaEntity.isPresent()){
            throw new ParamNotFound("Id pelicula no valido");
        }
        peliculaMapper.peliculaEntityRefreshValues(peliculaEntity.get(),dto);
        Pelicula peliculaSaved = peliculaRepositorio.save(peliculaEntity.get());
        PeliculaDTO peliculaResult = peliculaMapper.peliculaEntity2DTO(peliculaSaved,false);
        return peliculaResult;
    }

    public void addPersonaje (Long id, Long personajeId){
        Pelicula peliculaEntity = peliculaRepositorio.getById(id);
        peliculaEntity.getPersonajes().size();
        Personaje personajeEntity = personajeServicio.getById(id);
        peliculaEntity.addPersonaje(personajeEntity);
        peliculaRepositorio.save(peliculaEntity);
    }

    public Pelicula getById(Long id){
        Optional<Pelicula> peliculaEntity = peliculaRepositorio.findById(id);
        if(!peliculaEntity.isPresent()){
            throw new ParamNotFound("Id pelicula no valido");
        }
        return peliculaEntity.get();
    }

    public PeliculaDTO getByDetails(Long id){
        Pelicula peliculaEntity = peliculaRepositorio.getById(id);
        PeliculaDTO peliculaResult = peliculaMapper.peliculaEntity2DTO(peliculaEntity,true);
        return peliculaResult;
    }

    public List<PeliculaDTO> getByFilters(String titulo, Set<Long> generos, String order){
        PeliculaFiltersDTO filtersDTO = new PeliculaFiltersDTO(titulo,generos,order);
        List<Pelicula> entities = peliculaRepositorio.findAll(this.peliculaSpecification.getByFiltersPelicula(filtersDTO));
        List<PeliculaDTO> dtos = peliculaMapper.peliculaEntityList2DTOList(entities,true);
        return dtos;
    }

    public void addGenero(Long id, Long generoId) {
    }


}
