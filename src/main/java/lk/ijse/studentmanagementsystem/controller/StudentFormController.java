package lk.ijse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import lk.ijse.studentmanagementsystem.entity.Student;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StudentFormController {
    @FXML private TextField studentIdField, nameField, phoneField, nicField, emailField;
    @FXML private TextArea addressField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TableView<Student> studentTable;
    @FXML private Label timeLabel;

    public void initialize() {
        genderComboBox.getItems().addAll("Male", "Female", "Other");

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
    private void btnSaveStudentOnAction() {
        // Implement save logic
    }

    @FXML
    private void btnClearStudentOnAction() {
        studentIdField.clear();
        nameField.clear();
        addressField.clear();
//        phoneField.clear();
        nicField.clear();
//        emailField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        phoneField.setText("+94");
        emailField.setText("gmail.com");
    }

    public void btnUpdateStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
    }
}
