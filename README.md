# FP-Yonder-MyCinema
A java application for managing multiple cinemas, created during training sessions at Fii Practic and continued individually.

- used PostgreSQL, Maven, Hibernate, Mockito, log4j, Spring Boot Scheduler, Spring Boot Mail, Velocity
- create a reservation for a movie instance in a certain cinema and during a certain time
- create an account in order to keep track of each user's reservations
- scheduling using Spring Boot Scheduler and Spring Boot Mail: sending an email at 7AM daily to every person that as a reservation for that day, with a text like the following:

```
Reservation today - Lord of the Rings

Dear Dana P,

This email is for reminding you about your reservation for today at Cinema Fantasy.

Reservation Details

Movie: Lord of the Rings
Starting time: 18:00
Number of tickets: 4

Please be present at the cinema 30 minutes before the movie starts in order to validate your reservation.

MyCinema Fii Practic
```
- used velocity for creating the email template
- log4j for logging
