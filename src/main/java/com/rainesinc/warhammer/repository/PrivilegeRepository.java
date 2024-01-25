package com.rainesinc.warhammer.repository;

import com.rainesinc.warhammer.entity.Privilege;
import com.rainesinc.warhammer.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {
    Privilege findByName(String name);
}
