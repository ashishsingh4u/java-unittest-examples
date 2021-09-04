package com.techienotes.services;

public class DummyNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        throw new AssertionError("Method is not implemented, hence dummy service is used. Call should not reach here");
    }
}
