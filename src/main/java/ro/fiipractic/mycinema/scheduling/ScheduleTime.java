package ro.fiipractic.mycinema.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Logger;

@Component
public class ScheduleTime {

    private static final Logger logger = Logger.getLogger(ScheduleTime.class.getName());
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        Date date = new Date();
        dateFormat.format(date);
        logger.info("The time is now " + date);
    }
}
