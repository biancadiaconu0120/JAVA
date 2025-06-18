package com.modul2.learning.service;

import com.modul2.learning.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender mailSender;


      //Notify librarian about an overdue book.
    public void notifyLibrarian(String librarianEmail, Reservation reservation) {
        String subject = "Overdue Reservation Alert";
        String body = "The book '" + reservation.getExemplary().getBook().getTitle() +
                "' has not been returned by user: " +
                reservation.getUser().getFirstName() + " " + reservation.getUser().getLastName() +
                ". Please contact them at " + reservation.getUser().getPhoneNumber();

        sendEmail(librarianEmail, subject, body);
    }

    //Notify also user to return the book
    public void notifyUser(String userEmail, Reservation reservation) {
        String subject = "Return Reminder";
        String body = "You have an overdue reservation for the book: " +
                reservation.getExemplary().getBook().getTitle() +
                ". Please return it as soon as possible.";

        sendEmail(userEmail, subject, body);
    }


    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
