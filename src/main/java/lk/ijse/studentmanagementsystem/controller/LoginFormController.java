package lk.ijse.studentmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;
import lk.ijse.studentmanagementsystem.util.Navigation;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane ancLogin;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBO userBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {
        boolean isValid = userBo.checkCredential(txtUserName.getText(), txtPassword.getText());

        if(isValid){
            new Alert(Alert.AlertType.INFORMATION, "Login successful").show();
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/global-form.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("Register Form");
            stage.show();

            ancLogin.getScene().getWindow().hide();
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
        }

    }

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register-form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Register Form");
        stage.show();

        ancLogin.getScene().getWindow().hide();

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnSignUp.requestFocus();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

}
