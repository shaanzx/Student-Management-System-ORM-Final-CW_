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

public class GlobalFormController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private Pane paginPane;

    @FXML
    private Button btnCourse;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnPurchase;

    @FXML
    private Button btnStudents;

    private final String activeStyle = "-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 16px; -fx-alignment: CENTER; -fx-padding: 10; -fx-font-family: impact;";
    private final String inactiveStyle = "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 16px; -fx-alignment: CENTER; -fx-padding: 10; -fx-font-family: impact;";

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane, "dashboard-form.fxml");
        updateButtonStyles(btnHome);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane,"student-form.fxml");
        updateButtonStyles(btnStudents);
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane,"courses-form.fxml");
        updateButtonStyles(btnCourse);
    }

    @FXML
    void btnPurchaseOnAction(ActionEvent event) throws IOException {
        Navigation.switchPaging(paginPane,"purchase-form.fxml");
        updateButtonStyles(btnPurchase);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        updateButtonStyles(btnLogout);
        Navigation.switchToPage( "login-form.fxml",mainPane);
    }

    private void updateButtonStyles(Button activeButton) {
        btnHome.setStyle(inactiveStyle);
        btnStudents.setStyle(inactiveStyle);
        btnCourse.setStyle(inactiveStyle);
        btnPurchase.setStyle(inactiveStyle);
        btnLogout.setStyle(inactiveStyle);
        activeButton.setStyle(activeStyle);
    }

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
}
