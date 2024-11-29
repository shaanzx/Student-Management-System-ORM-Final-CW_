package lk.ijse.studentmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;
import lk.ijse.studentmanagementsystem.tm.StudentTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserStudentFormController {

    @FXML
    private TableColumn<?, ?> ColStudentNIC;

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

    @FXML
    private TableView<StudentTM> tblStudent;

    @FXML
    private Label timeLabel;
    StudentBO studentBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);

    public void initialize(){
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
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
        ColStudentNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colStudentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colStudentGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colStudentAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }
}
