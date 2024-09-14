package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.service.FactionService;
import com.rainesinc.warhammer.service.MiniatureService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Controller
@RequestMapping("/miniatures")
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
        return "miniature_new_form";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable(name = "id") int id) throws NotFoundException {
        ModelAndView mav = new ModelAndView("miniature_edit_form");
        Miniature miniature = miniatureService.findMiniatureById(id);
        mav.addObject("miniature", miniature);
        Iterable<Faction> factionList = factionService.findAllFactions();
        mav.addObject("factionList", factionList);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("miniature") Miniature miniature){
         miniatureService.addMiniature(miniature);
         return "redirect:/";

    }

    @RequestMapping
    public String getMiniatures(Model model){
        List<Miniature> miniaturesList =
                StreamSupport
                .stream(miniatureService.findAllMiniatures().spliterator(),false).toList();
        model.addAttribute("miniaturesList", miniaturesList);
        return "index";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("miniature") Miniature miniature) {
            miniatureService.updateMiniature(miniature);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id){
        miniatureService.removeMiniatureById(id);
        return "redirect:/";
    }

}
