package com.mitocode.yt.demowebflux.controller;

import com.mitocode.yt.demowebflux.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/personas/")
public class PersonaController {

    private static final Logger log = LoggerFactory.getLogger(PersonaController.class);

    @GetMapping("/mostrar")
    public Mono<Persona> mostrar(){
        return Mono.just(new Persona(1, "Dan"));
    }
    @GetMapping
    public Flux<Persona> listar(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Dan"));
        personas.add(new Persona(1, "WH"));

        return Flux.fromIterable(personas);
    }

    @GetMapping("/response")
    public Mono<ResponseEntity<Flux<Persona>>> listEntity(){

        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Dan"));
        personas.add(new Persona(1, "WH"));

        Flux<Persona> personaFlux = Flux.fromIterable(personas);
        return Mono.just(ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personaFlux));
    }

    @DeleteMapping("/{modo}")
    public Mono<ResponseEntity<Void>> eliminar(@PathVariable("modo") Integer modo){
        return buscarPersona(modo)
                .flatMap( p -> eliminar(p)
                            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    public Mono<Void> eliminar(Persona persona){
        log.info("Eliminando " + persona.getIdPersona() + " - " + persona.getNombre());
        return Mono.empty();
    }

    public Mono<Persona> buscarPersona( Integer modo){

        if(modo == 1) {
            return Mono.just(new Persona(1, "DanWh"));
        }

        return Mono.empty();
    }
}
