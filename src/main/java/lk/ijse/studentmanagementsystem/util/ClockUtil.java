package lk.ijse.studentmanagementsystem.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClockUtil {

    public static void initializeClock(Label timeLabel, String timeFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
        Timeline clock = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime currentTime = LocalTime.now();
                    timeLabel.setText(currentTime.format(formatter));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
}
