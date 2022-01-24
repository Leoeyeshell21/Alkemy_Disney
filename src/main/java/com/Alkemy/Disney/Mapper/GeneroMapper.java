package com.Alkemy.Disney.Mapper;

import com.Alkemy.Disney.DTO.GeneroDTO;
import com.Alkemy.Disney.Entity.Genero;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public Genero generoDTO2Entity(GeneroDTO dto){

        Genero generoEntity = new Genero();
        generoEntity.setImagen(dto.getImagen());
        generoEntity.setNombre(dto.getNombre());
        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(Genero entity){
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }

    public List<GeneroDTO> generoEntity2DTOList(List<Genero> generoEmtities){
        List<GeneroDTO> dtos = new ArrayList<>();
        for(Genero genero : generoEmtities){
            dtos.add(generoEntity2DTO(genero));
        }
        return dtos;
    }
}
