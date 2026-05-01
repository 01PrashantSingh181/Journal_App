package com.coding_hustle.journalApp.repository;

import com.coding_hustle.journalApp.entity.JournalEntry;
import com.coding_hustle.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
    void deleteByUserName(String username);
}
