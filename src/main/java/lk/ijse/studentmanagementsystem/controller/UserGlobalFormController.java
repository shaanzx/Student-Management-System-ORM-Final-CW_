package lk.ijse.studentmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lk.ijse.studentmanagementsystem.util.Navigation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserGlobalFormController implements Initializable {

    @FXML
    private Button btnCourse;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnStudents;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Pane paginPane;

    private final String activeStyle = "-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16px; -fx-alignment: CENTER; -fx-padding: 10; -fx-font-family: impact;";
    private final String inactiveStyle = "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: CENTER; -fx-padding: 10; -fx-font-family: impact;";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paginPane.setVisible(true);
        try {
            Navigation.switchPaging(paginPane, "dashboard-form.fxml");
            updateButtonStyles(btnHome);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(paginPane, "dashboard-form.fxml");
            updateButtonStyles(btnHome);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
        try {
            updateButtonStyles(btnLogout);
            Navigation.switchToPage( "login-form.fxml",mainPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(paginPane,"user-student-form.fxml");
            updateButtonStyles(btnStudents);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(paginPane,"user-courses-form.fxml");
            updateButtonStyles(btnCourse);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateButtonStyles(Button activeButton) {
        btnHome.setStyle(inactiveStyle);
        btnStudents.setStyle(inactiveStyle);
        btnCourse.setStyle(inactiveStyle);
        btnLogout.setStyle(inactiveStyle);
        activeButton.setStyle(activeStyle);
    }

}
