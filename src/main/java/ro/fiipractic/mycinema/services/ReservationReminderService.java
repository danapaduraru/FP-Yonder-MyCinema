package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.Person;
import ro.fiipractic.mycinema.entities.Reservation;

import javax.mail.MessagingException;
import java.util.List;

public interface ReservationReminderService {

    void sendReservationReminderMail(Reservation reservation) throws MessagingException;

    void sendReservationMailsForToday() throws MessagingException;
}
