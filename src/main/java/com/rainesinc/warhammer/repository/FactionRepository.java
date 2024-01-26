package com.rainesinc.warhammer.repository;

import com.rainesinc.warhammer.entity.Faction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactionRepository extends CrudRepository<Faction, Integer> {
}
