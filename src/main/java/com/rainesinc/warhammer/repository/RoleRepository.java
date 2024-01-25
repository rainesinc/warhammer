package com.rainesinc.warhammer.repository;

import com.rainesinc.warhammer.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
