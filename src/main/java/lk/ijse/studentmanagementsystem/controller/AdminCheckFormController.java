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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;
import lk.ijse.studentmanagementsystem.util.Navigation;

import java.io.IOException;

public class AdminCheckFormController {
    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.USER);


    @FXML
    private Pane ancLogin;

    @FXML
    private JFXButton btnCheckAdmin;

    @FXML
    private TextField txtAdminId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnCheckAdminOnAction(ActionEvent event) {
        boolean isValid = userBO.checkAdmin(txtAdminId.getText(), txtPassword.getText());

        if (isValid) {
            try {
                Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/register-form.fxml"));
                Scene scene = new Scene(rootNode);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setTitle("Register Form");
                stage.show();

                ancLogin.getScene().getWindow().hide();
            } catch (IOException e) {
                new Alert(Alert.AlertType.ERROR, "You Are Not Admin").show();
                throw new RuntimeException(e);
            }
        }
    }

}
