package lk.ijse.studentmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.studentmanagementsystem.dto.UserDTO;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;
import lk.ijse.studentmanagementsystem.util.Validation;

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
        generateNextUserId();
        validationIntentFields();
    }

    private void validationIntentFields() {
        txtUserId.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());
        txtUserName.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());
        txtUserEmail.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());
        txtUserMobileNo.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());

    }

    private void validateUserForm() {
        boolean isValid = Validation.validateAllFields(txtUserId, txtUserName, txtUserEmail, txtUserMobileNo, txtPassword);

        if (isValid && cmbJobRole.getValue() != null) {
            btnRegister.setDisable(false);
        } else {
            btnRegister.setDisable(true);
        }
    }

    private void generateNextUserId() {
        try {
            txtUserId.setText(userBo.generateNewUserId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void clearTextField() {
        txtUserId.clear();
        txtUserName.clear();
        cmbJobRole.getSelectionModel().clearSelection();
        txtUserEmail.setText("gmail.com");
        txtUserMobileNo.setText("+94");
        txtPassword.clear();

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
            if (userBo.saveUser(user)) {
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
                clearTextField();
                generateNextUserId();
            } else {
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
