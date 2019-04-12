package ro.fiipractic.mycinema.services;

public interface ReservationReminderService {
    void sendReservationReminder(String to, String subject, String text);
}
