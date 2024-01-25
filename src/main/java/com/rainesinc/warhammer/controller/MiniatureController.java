package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.service.MiniatureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("readById/{id}") // READ by id
    public Miniature getMiniatureById(@PathVariable("id") int id) throws NotFoundException {
        return service.findMiniatureById(id);
    }

    @GetMapping // READ all
    public List<Miniature> getMiniatures(){
        return StreamSupport
                .stream(service.findAllMiniatures().spliterator(),false).toList();
    }

    @PutMapping("update") // UPDATE by miniature
    public void putMiniature(@Valid @RequestBody Miniature miniature){
        service.updateMiniature(miniature);
    }

    @DeleteMapping("deleteById/{id}") // DELETE by id
    public void deleteMiniatureById(@PathVariable("id") int id){
        service.removeMiniatureById(id);
    }

}
