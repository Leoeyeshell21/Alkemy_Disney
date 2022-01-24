package com.Alkemy.Disney.Service.Implements;

import com.Alkemy.Disney.DTO.PersonajeBasicDTO;
import com.Alkemy.Disney.DTO.PersonajeDTO;
import com.Alkemy.Disney.DTO.PersonajeFiltersDTO;
import com.Alkemy.Disney.Entity.Pelicula;
import com.Alkemy.Disney.Entity.Personaje;
import com.Alkemy.Disney.Exception.ParamNotFound;
import com.Alkemy.Disney.Mapper.PersonajeMapper;
import com.Alkemy.Disney.Repository.PersonajeRepository;
import com.Alkemy.Disney.Repository.Specification.PersonajeSpecification;
import com.Alkemy.Disney.Service.PeliculaService;
import com.Alkemy.Disney.Service.PersonajeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jnlp.PersistenceService;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImp implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepositorio;
    @Autowired
    private PersonajeSpecification personajeSpecification;
    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PeliculaService peliculaService;


    @Override
    public Personaje getById(Long id) {
        return null;
    }

    public PersonajeDTO getDetailsById(Long id){
        Optional<Personaje> personajeEntity = personajeRepositorio.findById(id);
        if(!personajeEntity.isPresent()){
            throw new ParamNotFound("Id personaje no valido");
        }
        PersonajeDTO personajeDTO = personajeMapper.personajeEntity2DTO(personajeEntity.get(),Boolean.TRUE);
        return personajeDTO;
    }

    public List<PersonajeBasicDTO> getAll(){
        List<Personaje> entities = personajeRepositorio.findAll();
        List<PersonajeBasicDTO> personajeBasicDTOS = personajeMapper.personajeEntitySet2BasicDTOList(entities);
        return personajeBasicDTOS;
    }

    public List<PersonajeDTO> getByFilters(String nombre, Integer edad, Set<Long> peliculas, String order){
        PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(nombre,edad,peliculas,order);
        List<Personaje> entities = personajeRepositorio.findAll(this.personajeSpecification.getByFilters(filtersDTO));
        List<PersonajeDTO> dtos = personajeMapper.personajeEntitySet2DTOList(entities,Boolean.TRUE);
        return dtos;
    }

    public PersonajeDTO save(PersonajeDTO personajeDTO){
        Personaje personajeEntity = personajeMapper.personajeDTO2Entity(personajeDTO);
        Personaje personajeSaved = personajeRepositorio.save(personajeEntity);
        PersonajeDTO personajeResult = personajeMapper.personajeEntity2DTO(personajeSaved,Boolean.FALSE);
        return personajeResult;
    }

    public PersonajeDTO update(Long id, PersonajeDTO personajeDTO){
        Optional<Personaje> personajeEntity = personajeRepositorio.findById(id);
        if(!personajeEntity.isPresent()){
            throw new ParamNotFound("Id personaje no valido");
        }
        personajeMapper.personajeEntityRefreshValues(personajeEntity.get(), personajeDTO);
        Personaje personajeSaved = personajeRepositorio.save(personajeEntity.get());
        PersonajeDTO personajeResult = personajeMapper.personajeEntity2DTO(personajeSaved, Boolean.FALSE);
        return personajeResult;
    }

    public void addPelicula(Long id, Long idPelicula){
        Personaje personajeEntity = personajeRepositorio.getById(id);
        personajeEntity.getPeliculas().size();
        Pelicula peliculaEntity = peliculaService.getById(idPelicula);
        personajeEntity.addPelicula(peliculaEntity);
        personajeRepositorio.save(personajeEntity);
    }

    public void removePelicula(Long id, Long idPelicula){
        Personaje personajeEntity = personajeRepositorio.getById(id);
        personajeEntity.getPeliculas().size();
        Pelicula peliculaEntity = peliculaService.getById(idPelicula);
        personajeEntity.removePelicula(peliculaEntity);
        personajeRepositorio.save(personajeEntity);
    }

    public void delete(Long id){
        personajeRepositorio.deleteById(id);
    }


}
