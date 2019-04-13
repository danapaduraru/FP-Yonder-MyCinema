package ro.fiipractic.mycinema.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.ReservationReminderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ReservationReminderServiceImpl implements ReservationReminderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MovieInstanceService movieInstanceService;

    private static final Logger logger = Logger.getLogger(ReservationReminderServiceImpl.class.getName());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void sendReservationReminderMail(String to, String subject, String text) {
        logger.info("ReservationReminderService sendReservationReminderMail method called: sent mail to " + to);
        SimpleMailMessage reservationReminder = new SimpleMailMessage();
        reservationReminder.setTo(to);
        reservationReminder.setSubject(subject);
        reservationReminder.setText(text);
        javaMailSender.send(reservationReminder);
    }

    @Override
    @Scheduled(cron = "0 0 7 * * *") // send daily at 7AM
    public void sendReservationsMails() {
        logger.info("ReservationReminderService sendReservationMails method called: sent all reservation mails for today");

        Date todayDate = new Date();
        String today = dateFormat.format(todayDate);

        List<MovieInstance> movieInstances = movieInstanceService.getAllMovieInstances();

        for (MovieInstance movieInstance : movieInstances) {
            String dateMovieInstance = movieInstance.getStartDate().substring(0, 10);
            if (dateMovieInstance.equals(today)) {
                List<Reservation> reservations = movieInstance.getReservations();
                for (Reservation reservation : reservations) {
                    sendReservationReminderMail(reservation.getPerson().getEmail(), "Movie Reservation Today", "Be there!");
                }
            }
        }
    }
}