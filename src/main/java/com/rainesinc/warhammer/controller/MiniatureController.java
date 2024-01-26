package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.service.FactionService;
import com.rainesinc.warhammer.service.MiniatureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Controller
public class MiniatureController {
    @Autowired
    private MiniatureService miniatureService;

    @Autowired
    private FactionService factionService;

    @RequestMapping("/new")
    public String showNewForm(Model model) {
        Miniature miniature = new Miniature();
        model.addAttribute("miniature", miniature);
        Iterable<Faction> factionList = factionService.findAllFactions();
        model.addAttribute("factionList", factionList);
        return "new_form";
    }

    @PostMapping // CREATE
    public Miniature postMiniature(@Valid @RequestBody Miniature miniature){
        return miniatureService.addMiniature(miniature);
    }

    @GetMapping("readById/{id}") // READ by id
    public Miniature getMiniatureById(@PathVariable("id") int id) throws NotFoundException {
        return miniatureService.findMiniatureById(id);
    }

    @RequestMapping // READ all
    public String getMiniatures(Model model){
        List<Miniature> miniaturesList =
                StreamSupport
                .stream(miniatureService.findAllMiniatures().spliterator(),false).toList();
        model.addAttribute("miniaturesList", miniaturesList);
        return "index";
    }

    @PutMapping("update") // UPDATE by miniature
    public void putMiniature(@Valid @RequestBody Miniature miniature){
        miniatureService.updateMiniature(miniature);
    }

    @DeleteMapping("deleteById/{id}") // DELETE by id
    public void deleteMiniatureById(@PathVariable("id") int id){
        miniatureService.removeMiniatureById(id);
    }

}
