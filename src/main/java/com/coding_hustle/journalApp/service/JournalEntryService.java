package com.coding_hustle.journalApp.service;

import com.coding_hustle.journalApp.entity.JournalEntry;
import com.coding_hustle.journalApp.entity.User;
import com.coding_hustle.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

//    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found"); // 👈 IMPORTANT
            }
            journalEntry.setDate(new Date());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry. ", e);
        }


    }

    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
    }




}
