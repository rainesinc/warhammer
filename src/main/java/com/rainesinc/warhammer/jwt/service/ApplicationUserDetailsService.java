package com.rainesinc.warhammer.jwt.service;

import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.exception.NotFoundException;
import com.rainesinc.warhammer.jwt.model.UserPrincipal;
import com.rainesinc.warhammer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        try {
            return new UserPrincipal(userService.findByEmail(email));
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    public User authenticate(String email, String password)
            throws NoSuchAlgorithmException, NotFoundException {
        if (
                email.isEmpty() || password.isEmpty()
        ) throw new BadCredentialsException("Unauthorized");

        var userEntity = userService.findByEmail(email);

        if (userEntity == null) throw new BadCredentialsException("Unauthorized");

        var verified = verifyPasswordHash(
                password,
                userEntity.getHash(),
                userEntity.getSalt()
        );

        if (!verified) throw new BadCredentialsException("Unauthorized");

        return userEntity;
    }

    private Boolean verifyPasswordHash(
            String password,
            byte[] storedHash,
            byte[] storedSalt
    ) throws NoSuchAlgorithmException {
        if (
                password.isBlank() || password.isEmpty()
        ) throw new IllegalArgumentException(
                "Password cannot be empty or whitespace only string."
        );

        if (storedHash.length != 64) throw new IllegalArgumentException(
                "Invalid length of password hash (64 bytes expected)"
        );

        if (storedSalt.length != 128) throw new IllegalArgumentException(
                "Invalid length of password salt (64 bytes expected)."
        );

        var md = MessageDigest.getInstance("SHA-512");
        md.update(storedSalt);

        var computedHash = md.digest(password.getBytes(StandardCharsets.UTF_8));

        for (int i = 0; i < computedHash.length; i++) {
            if (computedHash[i] != storedHash[i]) return false;
        }

        // The above for loop is the same as below

        return MessageDigest.isEqual(computedHash, storedHash);
    }
}
