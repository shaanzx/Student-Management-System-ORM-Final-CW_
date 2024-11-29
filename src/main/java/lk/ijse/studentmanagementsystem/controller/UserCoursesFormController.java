package lk.ijse.studentmanagementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.CourseBO;
import lk.ijse.studentmanagementsystem.tm.CourseTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserCoursesFormController {

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

    CourseBO courseBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.COURSE);

    public void initialize() {
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
        setCellValueFactory();
        loadAllCourses();

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
}
