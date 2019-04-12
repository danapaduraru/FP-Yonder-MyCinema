package ro.fiipractic.mycinema.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.fiipractic.mycinema.services.ReservationReminderService;

@RestController
@RequestMapping("/api/reservation-reminders")
public class ReservationReminderController {

    @Autowired
    ReservationReminderService reservationReminderService;

    @GetMapping
    public void sendReservationReminder() {
        reservationReminderService.sendReservationReminder("danapaduraru941@gmail.com","test","yay!");
    }
}
