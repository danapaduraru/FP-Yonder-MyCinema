package ro.fiipractic.mycinema.services.impl;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.ReservationReminderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ReservationReminderServiceImpl implements ReservationReminderService {

    @Autowired
    private MovieInstanceService movieInstanceService;

    @Autowired
    private JavaMailSender javaMailSender;

    private VelocityEngine velocityEngine;

    private static final Logger logger = Logger.getLogger(ReservationReminderServiceImpl.class.getName());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void sendReservationReminderMail(Reservation reservation) throws MessagingException {

        String to = reservation.getPerson().getEmail();
        String subject = "Reservation today - " + reservation.getMovieInstance().getMovie().getTitle();
        String text = " yay ";

        MimeMessage reservationReminder = javaMailSender.createMimeMessage();

        MimeMessageHelper reservationReminderHelper = new MimeMessageHelper(reservationReminder);
        reservationReminderHelper.setTo(to);
        reservationReminderHelper.setSubject(subject);
        reservationReminderHelper.setText(text);

        javaMailSender.send(reservationReminder);
        logger.info("ReservationReminderService sendReservationReminderMail method called: sent mail to " + reservation.getPerson().getEmail() + " with subject " + subject);
    }

    @Override
    @Scheduled(cron = "0 0/9 21 * * *") // send daily at 7AM
    public void sendReservationsMails() throws MessagingException {
        logger.info("ReservationReminderService sendReservationMails method called: sent all reservation mails for today");

        Date todayDate = new Date();
        String today = dateFormat.format(todayDate);

        List<MovieInstance> movieInstances = movieInstanceService.getAllMovieInstances();

        for (MovieInstance movieInstance : movieInstances) {
            String dateMovieInstance = movieInstance.getStartDate().substring(0, 10); // get only day of the reservation
            if (dateMovieInstance.equals(today)) {
                List<Reservation> reservations = movieInstance.getReservations();
                for (Reservation reservation : reservations) {
                    sendReservationReminderMail(reservation);
                }
            }
        }
    }


}