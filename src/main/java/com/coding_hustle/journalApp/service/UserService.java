package com.coding_hustle.journalApp.service;

import com.coding_hustle.journalApp.entity.JournalEntry;
import com.coding_hustle.journalApp.entity.User;
import com.coding_hustle.journalApp.repository.JournalEntryRepository;
import com.coding_hustle.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveNewUser(User user) {
//        String dbName = mongoTemplate.getDb().getName();
//        System.out.println("🔥 DB USED = " + dbName);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);

    }
    public void saveAdmin(User user) {
//        String dbName = mongoTemplate.getDb().getName();
//        System.out.println("🔥 DB USED = " + dbName);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);

    }
    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
