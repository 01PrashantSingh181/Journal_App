package com.coding_hustle.journalApp.repository;

import com.coding_hustle.journalApp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testGetUserForSA() {

        List<User> users = userRepository.getUserForSA();

        System.out.println(users);

    }
}