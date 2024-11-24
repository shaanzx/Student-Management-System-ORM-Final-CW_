package lk.ijse.studentmanagementsystem.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import lk.ijse.studentmanagementsystem.util.ClockUtil;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PurchaseFormController {

    @FXML
    private TableColumn<?, ?> advanceColumn;

    @FXML
    private TextField advancePaymentField;

    @FXML
    private TableColumn<?, ?> balanceColumn;

    @FXML
    private TextField balanceField;

    @FXML
    private TableView<?> courseDetailsTable;

    @FXML
    private TextField courseDurationField;

    @FXML
    private TextField courseFeeField;

    @FXML
    private TableColumn<?, ?> courseIdColumn;

    @FXML
    private TextField courseIdField;

    @FXML
    private TableColumn<?, ?> courseNameColumn;

    @FXML
    private ComboBox<?> courseNameComboBox;

    @FXML
    private TableColumn<?, ?> durationColumn;

    @FXML
    private TableColumn<?, ?> feeColumn;

    @FXML
    private DatePicker purchaseDatePicker;

    @FXML
    private TextField purchaseIdField;

    @FXML
    private TextField studentEmailField;

    @FXML
    private TextField studentNameField;

    @FXML
    private TextField studentNicField;

    @FXML
    private TextField studentTelField;

    @FXML
    private Label timeLabel;

    public void initialize() {
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");

    }

    @FXML
    void addPurchaseDetails(ActionEvent event) {

    }

    @FXML
    void addToCart(ActionEvent event) {

    }

    @FXML
    void buyCourse(ActionEvent event) {

    }

    @FXML
    void searchStudent(ActionEvent event) {

    }

}
