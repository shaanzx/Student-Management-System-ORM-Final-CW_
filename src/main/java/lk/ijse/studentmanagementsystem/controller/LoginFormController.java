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
import lk.ijse.studentmanagementsystem.util.Validation;

import java.io.IOException;
import java.sql.SQLException;

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


    public String userId;
    private static LoginFormController controller;

    public LoginFormController() {
        controller = this;
    }

    public static LoginFormController getInstance() {
        return controller;
    }

    UserBO userBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);

    public void initialize() {
        validationFields();
    }

    private void validationFields() {
        txtUserName.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> validateUserForm());
    }

    private void validateUserForm() {
        boolean isValid = Validation.validateAllFields(txtUserName, txtPassword);
        btnSignIn.setDisable(!isValid);
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) throws IOException {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields.").show();
            return;
        }

        try {
            boolean isValid = userBo.checkCredential(username, password);
            if (isValid) {
                boolean isAdmin = userBo.checkAdmin(username, password);
                String dashboardPath = isAdmin ? "/view/global-form.fxml" : "/view/user-global-form.fxml";

                Parent rootNode = FXMLLoader.load(this.getClass().getResource(dashboardPath));
                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Dashboard");
                stage.show();
                new Alert(Alert.AlertType.INFORMATION, "Login Success").show();

                ancLogin.getScene().getWindow().hide();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password. Please try again.").show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while logging in. Please try again.").show();
        }
    }


    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/adminCheck-form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Register Form");
        stage.show();

       // ancLogin.getScene().getWindow().hide();

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        btnSignIn.requestFocus();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

}
