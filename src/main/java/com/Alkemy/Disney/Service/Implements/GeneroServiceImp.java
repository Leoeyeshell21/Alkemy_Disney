package com.Alkemy.Disney.Service.Implements;

import com.Alkemy.Disney.DTO.GeneroDTO;
import com.Alkemy.Disney.Entity.Genero;
import com.Alkemy.Disney.Mapper.GeneroMapper;
import com.Alkemy.Disney.Repository.GeneroRepository;
import com.Alkemy.Disney.Service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImp implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private GeneroRepository generoRepository;


    public GeneroDTO save(GeneroDTO dto){
        Genero generoEntity = generoMapper.generoDTO2Entity(dto);
        Genero generoSaved = generoRepository.save(generoEntity);
        GeneroDTO generoResult = generoMapper.generoEntity2DTO(generoSaved);
        return generoResult;
    }

    public List<GeneroDTO> getAllGeneros() {
        List<Genero> generoEntities = generoRepository.findAll();
        List<GeneroDTO> generoResults = generoMapper.generoEntity2DTOList(generoEntities);
        return generoResults;
    }
}
