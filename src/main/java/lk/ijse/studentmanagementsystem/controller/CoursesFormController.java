package lk.ijse.studentmanagementsystem.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CoursesFormController {

    @FXML
    private Button btnClearCourse;

    @FXML
    private Button btnDeleteCourse;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextField courseDurationField;

    @FXML
    private TextField courseFeeField;

    @FXML
    private TextField courseIdField;

    @FXML
    private TextField courseNameField;

    @FXML
    private TableView<?> courseTable;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TableColumn<?, ?> durationColumn;

    @FXML
    private TableColumn<?, ?> feeColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Label timeLabel;

    public void initialize() {

        // Set up table columns
        // ...

        // Set up time display
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeLabel.setText(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }

    @FXML
    void btnClearCourseOnAction(ActionEvent event) {
        courseIdField.clear();
        courseNameField.clear();
        descriptionField.clear();
        courseFeeField.clear();
        courseDurationField.clear();
        courseFeeField.setText("Rs.");
    }

    @FXML
    void btnDeleteCourseOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveCourseOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCourseOnAction(ActionEvent event) {

    }

}
