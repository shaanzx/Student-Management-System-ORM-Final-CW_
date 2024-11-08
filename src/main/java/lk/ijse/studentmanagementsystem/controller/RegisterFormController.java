package lk.ijse.studentmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterFormController {


    @FXML
    private AnchorPane ancRegister;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserMobileNo;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        btnSignIn.requestFocus();
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/login-form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Register Form");
        stage.show();

        ancRegister.getScene().getWindow().hide();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnRegister.requestFocus();
    }

    @FXML
    void txtUserEmailOnAction(ActionEvent event) {
        txtUserMobileNo.requestFocus();
    }

    @FXML
    void txtUserMobileNoOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtUserEmail.requestFocus();
    }

}
