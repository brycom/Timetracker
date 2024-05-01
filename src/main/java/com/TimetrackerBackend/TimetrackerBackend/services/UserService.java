package com.TimetrackerBackend.TimetrackerBackend.services;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.TimetrackerBackend.TimetrackerBackend.models.Task;
import com.TimetrackerBackend.TimetrackerBackend.models.User;

@Service
public class UserService {
    private final MongoOperations mongoOperations;

    public UserService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public User createUser(User user) {
        return mongoOperations.insert(user);
    }

    public List<User> getUsers() {
        return mongoOperations.findAll(User.class);
    }

    public int getTotalTimeInMinutes(String id) {

        return mongoOperations.findById(id, User.class).getTotalTimeInMinutes();
    }

    public User login(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

}
