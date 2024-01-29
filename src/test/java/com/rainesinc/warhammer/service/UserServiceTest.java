package com.rainesinc.warhammer.service;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.repository.UserRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private UserService userService;

    @BeforeEach
    public void setup(){
        userService = new UserService(userRepository, roleRepository);
    }

    @Test
    public void shouldFindAllUsers() throws BadRequestException, NoSuchAlgorithmException {

        User user = new User();
        user.setEmail("admin@rainesinc.com");
        user.setPassword("password");

        userService.createUser(user);
        Iterable<User> userIterable = userService.findAllUsers();
        User savedUser = userIterable.iterator().next();

        assertThat(savedUser).isNotNull();
    }
}
