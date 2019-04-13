package ro.fiipractic.mycinema.services.impl;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.Reservation;
import ro.fiipractic.mycinema.services.MovieInstanceService;
import ro.fiipractic.mycinema.services.ReservationReminderService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

@Service
public class ReservationReminderServiceImpl implements ReservationReminderService {

    @Autowired
    private MovieInstanceService movieInstanceService;

    @Autowired
    private JavaMailSender javaMailSender;

    private VelocityEngine velocityEngine = new VelocityEngine();

    private static final Logger logger = Logger.getLogger(ReservationReminderServiceImpl.class.getName());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void sendReservationReminderMail(Reservation reservation) throws MessagingException {

        String to = reservation.getPerson().getEmail();
        String subject = "Reservation today - " + reservation.getMovieInstance().getMovie().getTitle();

        Properties properties = new Properties();
        properties.put("file.resource.loader.path", "src/main/resources/");
        velocityEngine.init(properties);

        MimeMessage reservationReminder = javaMailSender.createMimeMessage();
        MimeMessageHelper reservationReminderHelper = new MimeMessageHelper(reservationReminder);
        VelocityContext velocityContext = new VelocityContext();
        Template template = velocityEngine.getTemplate("templates/reservation-email-template.vm");

        velocityContext.put("personFullName", reservation.getPerson().getFullName());
        velocityContext.put("cinemaTitle", reservation.getMovieInstance().getCinema().getName());
        velocityContext.put("movieTitle", reservation.getMovieInstance().getMovie().getTitle());
        velocityContext.put("startTime", reservation.getMovieInstance().getStartDate().substring(11));
        velocityContext.put("tickets", reservation.getNumberOfTickets());

        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);

        reservationReminderHelper.setTo(to);
        reservationReminder.setSubject(subject);
        reservationReminder.setText(stringWriter.toString());

        javaMailSender.send(reservationReminder);
        logger.info("ReservationReminderService sendReservationReminderMail method called: sent mail to " + reservation.getPerson().getEmail() + " with subject " + subject);
    }

    @Override
    @Scheduled(cron = "0 0 7 * * *") // send daily at 7AM
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