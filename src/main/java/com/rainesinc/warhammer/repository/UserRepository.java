package com.rainesinc.warhammer.repository;

import com.rainesinc.warhammer.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
