package lk.ijse.studentmanagementsystem.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.CourseBO;
import lk.ijse.studentmanagementsystem.tm.CourseTM;
import lk.ijse.studentmanagementsystem.tm.StudentTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;
import lk.ijse.studentmanagementsystem.util.Validation;

import java.sql.SQLException;
import java.util.ArrayList;

public class CoursesFormController {

    @FXML
    private Button btnClearCourse;

    @FXML
    private Button btnDeleteCourse;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseFee;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSeats;

    @FXML
    private TableView<CourseTM> tblCourses;

    @FXML
    private Label timeLabel;

    @FXML
    private TextField txtCourseDescription;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtTotalSeats;

    CourseBO courseBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.COURSE);

    public void initialize() {
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
        generateNextCourserId();
        setCellValueFactory();
        loadAllCourses();
        validationIntentFields();
    }

    private void validationIntentFields() {
        txtCourseId.textProperty().addListener((observable, oldValue, newValue) -> validateCourseForm());
        txtCourseName.textProperty().addListener((observable, oldValue, newValue) -> validateCourseForm());
        txtTotalSeats.textProperty().addListener((observable, oldValue, newValue) -> validateCourseForm());
        txtCourseDescription.textProperty().addListener((observable, oldValue, newValue) -> validateCourseForm());
        txtCourseDuration.textProperty().addListener((observable, oldValue, newValue) -> validateCourseForm());
        txtCourseFee.textProperty().addListener((observable, oldValue, newValue) -> validateCourseForm());
    }

    private void validateCourseForm() {
        boolean isCourseIdValid = Validation.validateCourseFields(txtCourseId, txtCourseName, txtTotalSeats, txtCourseDescription, txtCourseDuration, txtCourseFee);
        if (isCourseIdValid) {
            btnSave.setDisable(false);
            btnUpdate.setDisable(false);
        }else {
            btnSave.setDisable(true);
            btnUpdate.setDisable(true);
        }
    }

    private void loadAllCourses() {
        tblCourses.getItems().clear();
        try {
            ArrayList<CourseDTO> allCourses = courseBO.getAllCourses();
            for (CourseDTO courseDTO : allCourses) {
                tblCourses.getItems().add(new CourseTM(
                        courseDTO.getCourseId(),
                        courseDTO.getCourseName(),
                        courseDTO.getCourseSeats(),
                        courseDTO.getCourseDescription(),
                        courseDTO.getCourseDuration(),
                        courseDTO.getCourseFee()
                ));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSeats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    private void generateNextCourserId() {
        try {
            txtCourseId.setText(courseBO.generateNextCourseId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearCourseOnAction(ActionEvent event) {
        generateNextCourserId();
        txtCourseName.clear();
        txtTotalSeats.clear();
        txtCourseDescription.clear();
        txtCourseDuration.setText("Month");
        txtCourseFee.setText("Rs.");
        btnSave.setDisable(false);
        txtCourseId.setDisable(false);
    }

    @FXML
    void btnDeleteCourseOnAction(ActionEvent event) {
        txtCourseId.setDisable(false);
        String courseId = txtCourseId.getText().trim();

        if (courseId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID").show();
            return;
        }

        try {
            if (!courseBO.isCourseExists(courseId)) {
                new Alert(Alert.AlertType.ERROR, "Course does not exist").show();
                return;
            }

            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this course?", ButtonType.YES, ButtonType.NO);
            confirmation.setTitle("Delete Confirmation");
            confirmation.setHeaderText(null);

            ButtonType result = confirmation.showAndWait().orElse(ButtonType.NO);
            if (result == ButtonType.YES) {
                if (courseBO.deleteCourse(courseId)) {
                    new Alert(Alert.AlertType.INFORMATION, "Course deleted successfully").show();
                    btnClearCourseOnAction(null);
                    generateNextCourserId();
                    loadAllCourses();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to delete course").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while deleting the course: " + e.getMessage()).show();
        }
    }


    @FXML
    void btnSaveCourseOnAction(ActionEvent event) {
        int totalSeats = Integer.parseInt(txtTotalSeats.getText().trim());


        CourseDTO courseDTO = new CourseDTO(
                txtCourseId.getText(),
                txtCourseName.getText(),
                totalSeats,
                txtCourseDescription.getText(),
                txtCourseDuration.getText(),
                txtCourseFee.getText()
        );

        try {
            if (courseBO.saveCourse(courseDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Course saved successfully").show();
                btnClearCourseOnAction(null);
                generateNextCourserId();
                loadAllCourses();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save course").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchCourseOnAction(ActionEvent event) {
        String courseId = txtCourseId.getText().trim();

        if (courseId == null || courseId.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter a Course ID").show();
            return;
        }

        try {
            CourseDTO course = courseBO.searchCourse(courseId);
            if (course != null) {
                txtCourseId.setText(course.getCourseId());
                txtCourseName.setText(course.getCourseName());
                txtTotalSeats.setText(String.valueOf(course.getCourseSeats()));
                txtCourseDescription.setText(course.getCourseDescription());
                txtCourseDuration.setText(course.getCourseDuration());
                txtCourseFee.setText(course.getCourseFee());
            } else {
                new Alert(Alert.AlertType.WARNING, "No course found with ID: " + courseId).show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateCourseOnAction(ActionEvent event) {
        CourseDTO courseDTO = new CourseDTO(
                txtCourseId.getText(),
                txtCourseName.getText(),
                Integer.parseInt(txtTotalSeats.getText()),
                txtCourseDescription.getText(),
                txtCourseDuration.getText(),
                txtCourseFee.getText()
        );

        try {
            if (courseBO.updateCourse(courseDTO)) {
                new Alert(Alert.AlertType.INFORMATION, "Course updated successfully").show();
                btnClearCourseOnAction(null);
                generateNextCourserId();
                loadAllCourses();

            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update course").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblClickOnAction(MouseEvent event) {
        if (txtCourseId != null) {
            btnSave.setDisable(true);
            //btnSearch.setDisable(true);
            txtCourseId.setDisable(true);
        }
        TablePosition tp = tblCourses.getSelectionModel().getSelectedCells().get(0);
        int row = tp.getRow();
        ObservableList<TableColumn<CourseTM, ?>> columns = tblCourses.getColumns();
        txtCourseId.setText(columns.get(0).getCellData(row).toString());
        txtCourseName.setText(columns.get(1).getCellData(row).toString());
        txtTotalSeats.setText(columns.get(2).getCellData(row).toString());
        txtCourseDescription.setText(columns.get(3).getCellData(row).toString());
        txtCourseDuration.setText(columns.get(4).getCellData(row).toString());
        txtCourseFee.setText(columns.get(5).getCellData(row).toString());
        tblCourses.setCursor(Cursor.HAND);
    }

    @FXML
    void txtSearchCourseById(ActionEvent event) {
        btnSearchCourseOnAction(null);
    }

}
