package com.Alkemy.Disney.Controller;

import com.Alkemy.Disney.DTO.PersonajeBasicDTO;
import com.Alkemy.Disney.DTO.PersonajeDTO;
import com.Alkemy.Disney.Mapper.PersonajeMapper;
import com.Alkemy.Disney.Service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeServicio;


    @GetMapping("/all")
    public ResponseEntity<List<PersonajeBasicDTO>> getAll(){
        List<PersonajeBasicDTO> personajes = personajeServicio.getAll();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id){
        PersonajeDTO personaje = personajeServicio.getDetailsById(id);
        return ResponseEntity.ok(personaje);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<PersonajeDTO>> getDetailsByFilter(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Set<Long> peliculas,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<PersonajeDTO> personajes = personajeServicio.getByFilters(nombre,edad,peliculas,order);
        return ResponseEntity.ok(personajes);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeResult = personajeServicio.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestParam PersonajeDTO personaje){
        PersonajeDTO personajeResult = personajeServicio.update(id,personaje);
        return ResponseEntity.ok().body(personajeResult);
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personajeServicio.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/pelicula/{idpelicula}")
    public ResponseEntity<Void> addPelicula(@PathVariable Long id, @PathVariable Long idPelicula){
        personajeServicio.addPelicula(id,idPelicula);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/pelicula/{idPelicula}")
    public ResponseEntity<Void> removePelicula(@PathVariable Long id, @PathVariable Long idPelicula){
        personajeServicio.removePelicula(id, idPelicula);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
