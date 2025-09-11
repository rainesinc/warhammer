package com.rainesinc.warhammer.controller;

import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.service.MiniatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/miniatures")
public class RestMiniatureController {

    @Autowired
    private MiniatureService miniatureService;

    @GetMapping // Handles GET requests to /api/miniatures
    public List<Miniature> getAllMiniatures() {
        List<Miniature> miniaturesList =
                StreamSupport
                        .stream(miniatureService.findAllMiniatures().spliterator(),false).toList();
        return miniaturesList;
    }

}
