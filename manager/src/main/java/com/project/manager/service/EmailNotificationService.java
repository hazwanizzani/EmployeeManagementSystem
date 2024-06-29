package com.project.manager.service;

import com.project.manager.repository.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

    @Service
    public class EmailNotificationService implements NotificationService
    {
        private final String emailFrom;
        public EmailNotificationService(@Value("${notification.email.from}") String emailFrom)
        {
            this.emailFrom = emailFrom;
        }

        public void emailNotification(String message)
        {
            System.out.println("Sending email from " + emailFrom + ": " + message);
        }



}
