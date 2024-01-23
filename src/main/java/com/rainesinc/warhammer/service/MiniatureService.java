package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.repository.MiniatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MiniatureService {
    @Autowired
    private MiniatureRepository repo;

    public Iterable<Miniature> findAllMiniatures() {
        return repo.findAll();
    }

    public void removeMiniatureById(int id){
        repo.deleteById(id);
    }

    public Miniature addMiniature(Miniature miniature){
        return repo.save(miniature);
    }

    public void updateMiniature(Miniature miniature){
        repo.save(miniature);
    }

    public Optional<Miniature> findMiniatureById(final int id){
        return repo.findById(id);
    }

}
