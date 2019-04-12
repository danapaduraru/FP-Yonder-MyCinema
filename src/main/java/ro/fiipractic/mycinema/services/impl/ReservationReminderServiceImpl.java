package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.services.ReservationReminderService;

@Service
public class ReservationReminderServiceImpl implements ReservationReminderService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendReservationReminder(String to, String subject, String text) {
        SimpleMailMessage reservationReminder = new SimpleMailMessage();
        reservationReminder.setTo(to);
        reservationReminder.setSubject(subject);
        reservationReminder.setText(text);
        javaMailSender.send(reservationReminder);
    }
}