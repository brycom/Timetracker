package com.TimetrackerBackend.TimetrackerBackend.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TimetrackerBackend.TimetrackerBackend.models.User;

@Service
public class UserService implements UserDetailsService {

    private final MongoOperations mongoOperations;

    //private PasswordEncoder passwordEncoder;

    public UserService(MongoOperations mongoOperations/* , PasswordEncoder passwordEncoder */) {
        this.mongoOperations = mongoOperations;
        //this.passwordEncoder = passwordEncoder;
    }

    public List<User> getUsers() {
        return mongoOperations.findAll(User.class);
    }

    public int getTotalTimeInMinutes(String id) {

        return mongoOperations.findById(id, User.class).getTotalTimeInMinutes();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("userservice");
        Query query = Query.query(Criteria.where("username").is(username));
        User user = mongoOperations.findOne(query, User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
