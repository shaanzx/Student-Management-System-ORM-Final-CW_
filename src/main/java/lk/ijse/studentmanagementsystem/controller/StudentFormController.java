package lk.ijse.studentmanagementsystem.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.dto.UserDTO;
import lk.ijse.studentmanagementsystem.entity.Student;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;
import lk.ijse.studentmanagementsystem.tm.StudentTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;
import lk.ijse.studentmanagementsystem.util.Validation;

import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class StudentFormController {
    @FXML private TextField txtStudentId, txtStudentName, txtStudentTel, txtStudentNIC, txtStudentEmail,txtStudentAddress;
    @FXML private ComboBox<String> genderComboBox;
    @FXML private TableView<StudentTM> tblStudent;
    @FXML private Label timeLabel;

    @FXML
    private Button btnClearStudent;

    @FXML
    private Button btnDeleteStudent;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSearch;

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
    private TableColumn<?, ?> ColStudentNIC;

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
        validationIntentFields();
    }

    private void validationIntentFields() {
        txtStudentId.textProperty().addListener((observable, oldValue, newValue) -> validateStudentForm());
        txtStudentName.textProperty().addListener((observable, oldValue, newValue) -> validateStudentForm());
        txtStudentEmail.textProperty().addListener((observable, oldValue, newValue) -> validateStudentForm());
        txtStudentTel.textProperty().addListener((observable, oldValue, newValue) -> validateStudentForm());
        txtStudentNIC.textProperty().addListener((observable, oldValue, newValue) -> validateStudentForm());
        txtStudentAddress.textProperty().addListener((observable, oldValue, newValue) -> validateStudentForm());
    }

    private void validateStudentForm() {
        boolean isValid = Validation.validateAllFields(txtStudentId, txtStudentName, txtStudentEmail, txtStudentTel, txtStudentNIC, txtStudentAddress);

        if (isValid && genderComboBox.getValue() != null) {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false); // Disable the update btn
        } else {
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
        }
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
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to save student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void btnClearStudentOnAction() {
        generateNextStudentId();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentNIC.clear();
        genderComboBox.getSelectionModel().clearSelection();
        txtStudentTel.setText("+94");
        txtStudentEmail.setText("gmail.com");
        txtStudentId.setDisable(false);
        btnSave.setDisable(false);
        btnDeleteStudent.setDisable(false);
    }

    @FXML
    public void btnUpdateStudentOnAction(ActionEvent actionEvent) {
        StudentDTO studentDTO = new StudentDTO(
                txtStudentId.getText(),
                txtStudentName.getText(),
                txtStudentTel.getText(),
                txtStudentNIC.getText(),
                txtStudentEmail.getText(),
                genderComboBox.getValue(),
                txtStudentAddress.getText(),
                u1);

        try {
            if (studentBO.updateStudent(studentDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Student updated successfully").show();
                btnClearStudentOnAction();
                generateNextStudentId();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to update student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void btnDeleteStudentOnAction(ActionEvent actionEvent) {
        String studentId = txtStudentId.getText();

        try {
            boolean isDeleted = studentBO.deleteStudent(studentId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Student deleted successfully").show();
                btnClearStudentOnAction();
                generateNextStudentId();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete student").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void tblStudentclickOnAction(MouseEvent mouseEvent) {
        if (txtStudentId != null) {
            btnSave.setDisable(true);
            //btnSearch.setDisable(true);
            txtStudentId.setDisable(true);
        }
        TablePosition tp = tblStudent.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<StudentTM, ?> > columns = tblStudent.getColumns();
        txtStudentId.setText(columns.get(0).getCellData(row).toString());
        txtStudentName.setText(columns.get(1).getCellData(row).toString());
        txtStudentTel.setText(columns.get(2).getCellData(row).toString());
        txtStudentNIC.setText(columns.get(3).getCellData(row).toString());
        txtStudentEmail.setText(columns.get(4).getCellData(row).toString());
        genderComboBox.setValue(columns.get(5).getCellData(row).toString());
        txtStudentAddress.setText(columns.get(6).getCellData(row).toString());
        tblStudent.setCursor(Cursor.HAND);
    }

    @FXML
    public void btnSearchStudentOnAction(ActionEvent actionEvent) {
        String studentNIC = txtStudentNIC.getText();
        try {
            StudentDTO student = studentBO.searchStudentByNic(studentNIC);
            if (student != null) {
                txtStudentId.setText(student.getId());
                txtStudentName.setText(student.getName());
                txtStudentTel.setText(student.getPhoneNo());
                txtStudentNIC.setText(student.getNic());
                txtStudentEmail.setText(student.getGmail());
                genderComboBox.setValue(student.getGender());
                txtStudentAddress.setText(student.getAddress());
            } else {
                new Alert(Alert.AlertType.WARNING, "No student found with NIC: " + studentNIC).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void txtSearchStudentByIdOnAction(ActionEvent event) {
        String studentId = txtStudentId.getText();
        try {
            StudentDTO student = studentBO.searchStudent(studentId);
            if (student != null) {
                txtStudentId.setText(student.getId());
                txtStudentName.setText(student.getName());
                txtStudentTel.setText(student.getPhoneNo());
                txtStudentNIC.setText(student.getNic());
                txtStudentEmail.setText(student.getGmail());
                genderComboBox.setValue(student.getGender());
                txtStudentAddress.setText(student.getAddress());
            } else {
                new Alert(Alert.AlertType.WARNING, "No student found with ID: " + studentId).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void txtSearchStudentByNICOnAction(ActionEvent actionEvent) {
        String studentNIC = txtStudentNIC.getText();
        try {
            StudentDTO student = studentBO.searchStudentByNic(studentNIC);
            if (student != null) {
                txtStudentId.setText(student.getId());
                txtStudentName.setText(student.getName());
                txtStudentTel.setText(student.getPhoneNo());
                txtStudentNIC.setText(student.getNic());
                txtStudentEmail.setText(student.getGmail());
                genderComboBox.setValue(student.getGender());
                txtStudentAddress.setText(student.getAddress());
            } else {
                new Alert(Alert.AlertType.WARNING, "No student found with NIC: " + studentNIC).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
