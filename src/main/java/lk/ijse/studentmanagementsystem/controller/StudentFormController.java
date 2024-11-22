package lk.ijse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.entity.Student;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class StudentFormController {
    @FXML private TextField studentIdField, nameField, phoneField, nicField, emailField;
    @FXML private TextArea addressField;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TableView<Student> studentTable;
    @FXML private Label timeLabel;

    private final StudentBO studentBO;

    public StudentFormController() {
        this.studentBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);
    }

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
        StudentDTO student = new StudentDTO(
                studentIdField.getText(),
                nameField.getText(),
                phoneField.getText(),
                nicField.getText(),
                emailField.getText(),
                genderComboBox.getValue(),
                addressField.getText()
        );

        try {
            if (studentBO.saveStudent(student)) {
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
