package com.rainesinc.warhammer.service;

import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.exception.NotFoundException;
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
    private UserRepository repo;

    public Iterable<User> findAllUsers() {
        return repo.findAll();
    }

    public void removeUserById(int id){
        repo.deleteById(id);
    }

    public User createUser(User user, String password)
            throws NoSuchAlgorithmException, BadRequestException {
        if(password.isBlank()) throw new IllegalArgumentException("Password is required.");

        var emailExists = repo.emailExists(user.getEmail());
        if(emailExists) throw new BadRequestException("Email " + user.getEmail() + " is taken.");

        byte[] salt = createSalt();
        byte[] hash = createPasswordHash(password, salt);

        user.setSalt(salt);
        user.setHash(hash);

        return repo.save(user);
    }

    public void updateUser(User user){
        repo.save(user);
    }

    public User findUserById(final int id) throws NotFoundException {
        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User with id = " + id + " was not found.")
                );
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