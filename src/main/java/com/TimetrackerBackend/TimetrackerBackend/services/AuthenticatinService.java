package com.TimetrackerBackend.TimetrackerBackend.services;

import java.util.HashSet;
import java.util.Set;

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
import com.TimetrackerBackend.TimetrackerBackend.models.Role;
import com.TimetrackerBackend.TimetrackerBackend.models.User;

@Service
@Transactional
public class AuthenticatinService {
    private AuthenticationManager authenticationManager;
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

    public User createUser(User user) throws Exception {
        Query q = Query.query(Criteria.where("username").is(user.getUsername()));
        User u = mongoOperations.findOne(q, User.class);
        if (u == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            Query query = Query.query(Criteria.where("authority").is("USER"));
            Role role = mongoOperations.findOne(query, Role.class);
            Set<Role> roles = new HashSet<Role>();
            roles.add(role);
            user.setRole(roles);
        } else
            throw new Exception("Username already exists");

        return mongoOperations.insert(user);
    }

    public LoginResponsDTO login(String username, String password) {

        try {
            System.out.println("Login");
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));

            String token = tokenService.GenerateJwt(auth);
            Query query = Query.query(Criteria.where("username").is(username));
            User user = mongoOperations.findOne(query, User.class);

            return new LoginResponsDTO(user, token);

        } catch (AuthenticationException e) {
            return new LoginResponsDTO(null, "Felaktigt användarnamn");
        }

    }
}
