package lk.ijse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

    public void initialize(){
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
        generateNextCourserId();
    }

    private void generateNextCourserId() {
        txtCourseId.setText();
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
