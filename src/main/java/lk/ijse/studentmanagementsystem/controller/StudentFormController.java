package lk.ijse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.entity.Student;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;
import lk.ijse.studentmanagementsystem.tm.StudentTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class StudentFormController {
    @FXML private TextField txtStudentId, txtStudentName, txtStudentTel, txtStudentNIC, txtStudentEmail;
    @FXML private TextArea txtStudentAddress;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TableView<StudentTM> tblStudent;
    @FXML private Label timeLabel;
    @FXML
    private TableColumn<?, ?> colStudentAddress;

    @FXML
    private TableColumn<?, ?> colStudentEmail;

    @FXML
    private TableColumn<?, ?> colStudentGender;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colStudentPhone;

    StudentBO studentBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);

    User u1 = new User();
    public void initialize() {
        genderComboBox.getItems().addAll("Male", "Female", "Other");
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
        generateNextStudentId();
        setCellValueFactory();
        loadAllStudents();
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudents();
            for (StudentDTO studentDTO : allStudent) {
                tblStudent.getItems().add(new StudentTM(
                        studentDTO.getId(),
                        studentDTO.getName(),
                        studentDTO.getAddress(),
                        studentDTO.getPhoneNo(),
                        studentDTO.getGmail(),
                        studentDTO.getNic(),
                        studentDTO.getGender()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStudentPhone.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStudentGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }


    private void generateNextStudentId() {
        try {
            txtStudentId.setText(studentBO.generateNextStudentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnSaveStudentOnAction() {
        StudentDTO student = new StudentDTO(
                txtStudentId.getText(),
                txtStudentName.getText(),
                txtStudentTel.getText(),
                txtStudentNIC.getText(),
                txtStudentEmail.getText(),
                genderComboBox.getValue(),
                txtStudentAddress.getText(),
                u1
        );

        try {
            if (studentBO.saveStudent(student)) {
                new Alert(Alert.AlertType.INFORMATION, "Student saved successfully").show();
                btnClearStudentOnAction();
                generateNextStudentId();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnClearStudentOnAction() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentNIC.clear();
        genderComboBox.getSelectionModel().clearSelection();
        txtStudentTel.setText("+94");
        txtStudentEmail.setText("gmail.com");
    }

    public void btnUpdateStudentOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
    }
}
