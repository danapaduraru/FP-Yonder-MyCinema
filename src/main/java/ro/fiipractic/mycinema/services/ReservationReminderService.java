package ro.fiipractic.mycinema.services;

import ro.fiipractic.mycinema.entities.MovieInstance;
import ro.fiipractic.mycinema.entities.Person;

import java.util.List;

public interface ReservationReminderService {

    void sendReservationReminderMail(String to, String subject, String text);

    void sendReservationsMails();
}
