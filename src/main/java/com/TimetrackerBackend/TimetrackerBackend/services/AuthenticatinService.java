package com.TimetrackerBackend.TimetrackerBackend.services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TimetrackerBackend.TimetrackerBackend.models.LoginResponsDTO;
import com.TimetrackerBackend.TimetrackerBackend.models.User;

@Service
@Transactional
public class AuthenticatinService {
    private final AuthenticationManager authenticationManager;
    private final MongoOperations mongoOperations;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;

    public AuthenticatinService(MongoOperations mongoOperations, AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            TokenService tokenService) {
        this.mongoOperations = mongoOperations;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public LoginResponsDTO login(String username, String password) {

        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            auth = authenticationManager.authenticate(auth);
            String token = tokenService.createToken(auth);
            Query query = Query.query(Criteria.where("username").is(username));
            User user = mongoOperations.findOne(query, User.class);

            return new LoginResponsDTO(user, token);

        } catch (AuthenticationException e) {
            return new LoginResponsDTO(null, "Felaktigt användarnamn");
        }

    }
}
