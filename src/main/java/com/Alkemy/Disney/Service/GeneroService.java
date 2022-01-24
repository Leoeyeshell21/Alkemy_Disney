package com.Alkemy.Disney.Service;

import com.Alkemy.Disney.DTO.GeneroDTO;

import java.util.List;

public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);

    List<GeneroDTO> getAllGeneros();

}
