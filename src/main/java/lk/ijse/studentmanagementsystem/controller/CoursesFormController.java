package lk.ijse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.CourseBO;
import lk.ijse.studentmanagementsystem.util.ClockUtil;

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
    private TableView<?> tblCourses;

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

    public void initialize(){
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
        generateNextCourserId();
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
        txtCourseId.clear();
        txtCourseName.clear();
        txtTotalSeats.clear();
        txtCourseDescription.clear();
        txtCourseDuration.setText("Month");
        txtCourseFee.setText("Rs.");
    }

    @FXML
    void btnDeleteCourseOnAction(ActionEvent event) {

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
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save course").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchCourseOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCourseOnAction(ActionEvent event) {

    }

    @FXML
    void tblClickOnAction(MouseEvent event) {

    }

    @FXML
    void txtSearchCourseById(ActionEvent event) {

    }

}
