package com.rainesinc.warhammer.repository;

import com.rainesinc.warhammer.entity.Miniature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniatureRepository extends CrudRepository<Miniature, Integer> {
}

