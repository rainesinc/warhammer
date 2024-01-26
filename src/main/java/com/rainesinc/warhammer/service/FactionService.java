package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Faction;
import com.rainesinc.warhammer.entity.Miniature;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.repository.FactionRepository;
import com.rainesinc.warhammer.repository.MiniatureRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FactionService {
    @Autowired
    private FactionRepository repo;

    public Iterable<Faction> findAllFactions() {
        return repo.findAll();
    }

    public void removeFactionById(int id){
        repo.deleteById(id);
    }

    public Faction addFaction(Faction faction){
        return repo.save(faction);
    }

    public void updateFaction(Faction faction){
        repo.save(faction);
    }

    public Faction findFactionById(final int id) throws NotFoundException {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Faction with id = " + id + " was not found.")
                );
    }

}
