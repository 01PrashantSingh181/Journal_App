package com.coding_hustle.journalApp.controller;

import com.coding_hustle.journalApp.scheduler.UserScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserScheduler userScheduler;

    @GetMapping("/test/mail")
    public String testMail() {
        userScheduler.fetchUsersAndSendSaMail();
        return "Mail Triggered";
    }
}