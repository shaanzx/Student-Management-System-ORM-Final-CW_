package lk.ijse.studentmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.studentmanagementsystem.dto.UserDTO;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;

import java.io.IOException;

public class RegisterFormController {


    @FXML
    private AnchorPane ancRegister;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private ComboBox<String> cmbJobRole;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserMobileNo;

    @FXML
    private TextField txtUserName;

    UserBO userBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);


    public void initialize() {
        cmbJobRole.getItems().addAll("Admin", "User");
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        btnSignIn.requestFocus();

        UserDTO user = new UserDTO(
                txtUserId.getText(),
                cmbJobRole.getValue(),
                txtUserName.getText(),
                txtUserEmail.getText(),
                txtUserMobileNo.getText(),
                txtPassword.getText()
        );

        try {
            if(userBo.saveUser(user)){
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "User not saved").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @FXML
    void txtUserIdOnAction(ActionEvent event) {
        cmbJobRole.requestFocus();
    }

    @FXML
    void cmbJobRoleOnAction(ActionEvent event) {
        txtUserName.requestFocus();
    }
}
