package com.coding_hustle.journalApp.scheduler;

import com.coding_hustle.journalApp.entity.JournalEntry;
import com.coding_hustle.journalApp.entity.User;
import com.coding_hustle.journalApp.repository.UserRepositoryImpl;
import com.coding_hustle.journalApp.service.EmailService;
import com.coding_hustle.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class UserScheduler {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryImpl userRepository;
    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;
    @Scheduled(cron = "0 0 9 ? * SUN *")
    public void fetchUsersAndSendSaMail() {
        List<User> users = userRepository.getUserForSA();
        for (User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream()
                    .filter(x ->
                            x.getDate().toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDateTime()
                                    .isAfter(LocalDateTime.now().minusDays(7))
                    ).map(x->x.getContent()).toList();
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(),"sentiment for last 7 days",sentiment);

        }

    }
}
