package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.service.MiniatureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("miniatures")
public class MiniatureController {
    @Autowired
    private MiniatureService service;

    @PostMapping // CREATE
    public Miniature postMiniature(@Valid @RequestBody Miniature miniature){
       return service.addMiniature(miniature);
    }

    @GetMapping("/{id}") // READ by id
    public Miniature getMiniatureById(@PathVariable("id") int id){
        return service.findMiniatureById(id);
    }

    @GetMapping // READ ALL
    public List<Miniature> getMiniatures(){
        return StreamSupport
                .stream(service.findAllMiniatures().spliterator(),false).toList();
    }

    @PutMapping("/{id}") // UPDATE by id
    public void putMiniature(@PathVariable("id") int id, @Valid @RequestBody Miniature miniature){
        if (!(id == miniature.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        service.updateMiniature(id, miniature);
    }

    @DeleteMapping("/{id}") // DELETE by id
    public void deleteMiniatureById(@PathVariable("id") int id){
        service.removeMiniatureById(id);
    }

}
