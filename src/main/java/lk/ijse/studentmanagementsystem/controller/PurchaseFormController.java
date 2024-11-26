package lk.ijse.studentmanagementsystem.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.CourseBO;
import lk.ijse.studentmanagementsystem.service.custom.PaymentBo;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;
import lk.ijse.studentmanagementsystem.tm.AddToCartTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;
import lk.ijse.studentmanagementsystem.util.Navigation;

import java.io.IOException;
import java.util.ArrayList;

public class PurchaseFormController {

    @FXML
    private TableColumn<?, ?> CourseFee;

    @FXML
    private Button btnAddNewCourse;

    @FXML
    private Button btnAddNewStudent;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBuyCourse;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnShowPurchaseDetails;

    @FXML
    private ComboBox<String> cmbSelectCourse;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableColumn<?, ?> colAdvance;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colCourseFee;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPurchaseID;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private Label lblShowEnoughMoney;

    @FXML
    private TableView<AddToCartTM> tblAddToCart;

    @FXML
    private StackPane paginPane;

    @FXML
    private Label timeLabel;

    @FXML
    private TextField txtAdvancePayment;

    @FXML
    private TextField txtAvailableSeats;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TextField txtCourseFee;

    @FXML
    private TextField txtCourseFeeBalance;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCustomerPaymentAmount;

    @FXML
    private TextField txtCustomerPaymentBalance;

    @FXML
    private TextField txtPurchaseId;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentNIC;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtStudentTel;

    @FXML
    private TextField txtTotalAmount;

    PaymentBo paymentBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.PAYMENT);
    CourseBO courseBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.COURSE);
    StudentBO studentBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);

    public void initialize() {
        ClockUtil.initializeClock(timeLabel, "HH:mm:ss");
        generateNextPurchaseId();
        loadAllCourses();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colPurchaseID.setCellValueFactory(new PropertyValueFactory<>("purchaseId"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advanceAmount"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balanceAmount"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        colRemove.setCellValueFactory(new PropertyValueFactory<>("remove"));
    }

    private void loadAllCourses() {
        ObservableList<String> courseIds = FXCollections.observableArrayList();

        try {
            ArrayList<CourseDTO> allCourses = courseBo.getAllCourses();

            for (CourseDTO course : allCourses) {
                courseIds.add(course.getCourseId());
            }

            cmbSelectCourse.setItems(courseIds);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextPurchaseId() {
        try {
            txtPurchaseId.setText(paymentBo.generateNextPurchaseId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddNewCourseOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(paginPane, "courses-form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddNewStudentOnAction(ActionEvent event) {
        try {
            Navigation.switchPaging(paginPane, "student-form.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnBuyCourseOnAction(ActionEvent event) {

    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        cmbSelectCourse.getSelectionModel().clearSelection();
        txtCourseId.clear();
        txtCourseFee.clear();
        txtCourseDuration.clear();
        txtAvailableSeats.clear();
        txtStudentNIC.clear();
        txtStudentName.clear();
        txtStudentTel.clear();
        txtStudentEmail.clear();
        txtPurchaseId.clear();
        generateNextPurchaseId();
        txtAdvancePayment.clear();
        txtCustomerPaymentAmount.clear();
        txtCustomerPaymentBalance.clear();
        txtTotalAmount.clear();
        txtCourseFeeBalance.clear();
        dpDate.setValue(null);
    }

    @FXML
    void btnSearchStudentByNicOnAction(ActionEvent event) {
        try {
            StudentDTO studentDTO = studentBo.searchStudentByNic(txtStudentNIC.getText());
            txtStudentName.setText(studentDTO.getName());
            txtStudentTel.setText(studentDTO.getPhoneNo());
            txtStudentEmail.setText(studentDTO.getGmail());

        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "No student found with NIC: " + txtStudentNIC.getText()).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnShowPurchaseDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectCourseOnAction(ActionEvent event) {
        try {
            CourseDTO courseDTO = courseBo.searchCourse(cmbSelectCourse.getValue());
            txtCourseId.setText(courseDTO.getCourseName());
            txtCourseFee.setText(String.valueOf(courseDTO.getCourseFee()));
            txtCourseDuration.setText(String.valueOf(courseDTO.getCourseDuration()));
            txtAvailableSeats.setText(String.valueOf(courseDTO.getCourseSeats()));
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, "Select course first and fill all fields").show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblShowEnoughMoneyOnAction(MouseEvent event) {

    }

    public void txtStudentNICOnAction(ActionEvent actionEvent) {
        btnSearch.requestFocus();
    }
}
