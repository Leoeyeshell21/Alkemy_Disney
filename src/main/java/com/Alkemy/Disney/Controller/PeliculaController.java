package com.Alkemy.Disney.Controller;

import com.Alkemy.Disney.DTO.PeliculaBasicDTO;
import com.Alkemy.Disney.DTO.PeliculaDTO;
import com.Alkemy.Disney.DTO.PersonajeBasicDTO;
import com.Alkemy.Disney.DTO.PersonajeDTO;
import com.Alkemy.Disney.Service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaServicio;

    @GetMapping
    public ResponseEntity<List<PeliculaBasicDTO>> getBasicList(){
        List<PeliculaBasicDTO> peliculas = peliculaServicio.getBasicList();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/alldetails")
    public ResponseEntity<List<PeliculaDTO>> getAllDetails(){
        List<PeliculaDTO> peliculas = peliculaServicio.getAllPeliculas();
        return ResponseEntity.ok(peliculas);
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO dto) {
        PeliculaDTO peliculaSaved = peliculaServicio.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> update(@PathVariable Long id, @RequestParam PeliculaDTO pelicula){
        PeliculaDTO peliculaResult = peliculaServicio.update(id,pelicula);
        return ResponseEntity.ok().body(peliculaResult);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        peliculaServicio.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/personaje/{idpersonaje}")
    public ResponseEntity<Void> addPersonaje(@PathVariable Long id, @PathVariable Long idPersonaje){
        peliculaServicio.addPersonaje(id,idPersonaje);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
