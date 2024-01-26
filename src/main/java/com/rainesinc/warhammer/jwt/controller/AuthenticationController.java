package com.rainesinc.warhammer.jwt.controller;

import com.rainesinc.warhammer.entity.User;
import com.rainesinc.warhammer.jwt.model.AuthenticationRequest;
import com.rainesinc.warhammer.jwt.model.AuthenticationResponse;
import com.rainesinc.warhammer.jwt.service.ApplicationUserDetailsService;
import com.rainesinc.warhammer.jwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/authenticate")
class AuthenticationController {

    private final JwtUtil jwtTokenUtil;
    private final ApplicationUserDetailsService userDetailsService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest req) throws Exception {
        User user;

        try {
            user = userDetailsService.authenticate(req.getEmail(), req.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        var userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        System.out.println(userDetails);
        var jwt = jwtTokenUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
