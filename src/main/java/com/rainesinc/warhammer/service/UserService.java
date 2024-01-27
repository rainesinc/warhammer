package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.Role;
import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.repository.RoleRepository;
import com.rainesinc.warhammer.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void removeUserById(int id){
        userRepository.deleteById(id);
    }

    public User createUser(String email, String password)
            throws NoSuchAlgorithmException, BadRequestException {
        User user = new User();
        user.setEmail(email);

        if(password.isBlank()) throw new IllegalArgumentException("Password is required.");

        byte[] salt = createSalt();
        byte[] hash = createPasswordHash(password, salt);

        user.setSalt(salt);
        user.setHash(hash);

        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.getRoles().add(roleUser);

        return userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public User findUserById(final int id) throws NotFoundException {
        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User with id = " + id + " was not found.")
                );
    }

    public User findByEmail(String email) throws NotFoundException{
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new NotFoundException("User with email = " + email + " was not found.");
        } else {
            return user;
        }
    }

    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);

        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
}
