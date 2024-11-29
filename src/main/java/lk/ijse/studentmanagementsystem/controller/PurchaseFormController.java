package lk.ijse.studentmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.dto.PaymentDTO;
import lk.ijse.studentmanagementsystem.dto.RegisterDTO;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.entity.Course;
import lk.ijse.studentmanagementsystem.entity.Register;
import lk.ijse.studentmanagementsystem.entity.Student;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.BOFactory;
import lk.ijse.studentmanagementsystem.service.custom.CourseBO;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;
import lk.ijse.studentmanagementsystem.service.custom.RegisterBO;
import lk.ijse.studentmanagementsystem.tm.AddToCartTM;
import lk.ijse.studentmanagementsystem.util.ClockUtil;
import lk.ijse.studentmanagementsystem.util.Navigation;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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
    private Button btnregister;

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
    private TableColumn<?, ?> colExpiredDate;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPurchaseID;

    @FXML
    private TableColumn<?, ?> colRemove;

    @FXML
    private TableColumn<?, ?> colStudentId;

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

    CourseBO courseBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.COURSE);
    StudentBO studentBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.STUDENT);
    RegisterBO registerBo = BOFactory.getBoFactory().getBO(BOFactory.BOType.REGISTER);


    private ObservableList<AddToCartTM> addToCartList = FXCollections.observableArrayList();

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
        colExpiredDate.setCellValueFactory(new PropertyValueFactory<>("expiredDate"));
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
            txtPurchaseId.setText(registerBo.generateNextPurchaseId());
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
        try {
            String purchaseId = txtPurchaseId.getText();
            String studentId = txtStudentNIC.getText();
            String courseId = cmbSelectCourse.getValue();
            double courseFee = Double.parseDouble(txtCourseFee.getText());
            double advanceAmount = Double.parseDouble(txtAdvancePayment.getText());
            double balanceAmount = Double.parseDouble(txtCourseFeeBalance.getText());

            LocalDate localRegisterDate = dpDate.getValue();
            Date registerDate = Date.valueOf(localRegisterDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(registerDate);
            calendar.add(Calendar.MONTH, 3);
            Date expiredDate = new Date(calendar.getTimeInMillis());

            JFXButton btnRemove = new JFXButton("Remove");
            btnRemove.setCursor(Cursor.HAND);
            btnRemove.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px;");

            btnRemove.setOnAction(e -> {
                int selectedIndex = tblAddToCart.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to remove this item?", yes, no).showAndWait();

                    if (result.orElse(no) == yes) {
                        addToCartList.remove(selectedIndex);
                        tblAddToCart.refresh();
                        calculateTotalAmount();
                        calculateCustomerPaymentBalanceAmount();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "No item selected to remove!").show();
                }
            });

            for (AddToCartTM item : addToCartList) {
                if (item.getCourseId().equals(courseId)) {
                    new Alert(Alert.AlertType.WARNING, "This course is already added to the cart!").show();
                    return;
                }
            }

            AddToCartTM addToCartTM = new AddToCartTM(
                    purchaseId,
                    studentId,
                    courseId,
                    courseFee,
                    advanceAmount,
                    balanceAmount,
                    registerDate,
                    expiredDate,
                    btnRemove
            );

            addToCartList.add(addToCartTM);
            tblAddToCart.setItems(addToCartList);
            tblAddToCart.refresh();
            calculateTotalAmount();
            clearInputFields();
            cmbSelectCourse.requestFocus();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: Fill all the required fields").show();
        }
    }


    @FXML
    void btnBuyCourseOnAction(ActionEvent event) {
        try {
            if (addToCartList.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Cart is empty! Add at least one course.").show();
                return;
            }

            String purchaseId = txtPurchaseId.getText();
            Date purchaseDate = Date.valueOf(dpDate.getValue());
            double totalAmount = Double.parseDouble(txtTotalAmount.getText());
            double paymentAmount = Double.parseDouble(txtCustomerPaymentAmount.getText());
            double balanceAmount = Double.parseDouble(txtCustomerPaymentBalance.getText());

            List<RegisterDTO> registerDTOS = new ArrayList<>();
            List<CourseDTO> courseDTOS = new ArrayList<>();
            Register firstRegisterDTO = null;

            for (AddToCartTM addToCart : addToCartList) {
                CourseDTO courseDTO = courseBo.searchCourse(addToCart.getCourseId());
                if (courseDTO == null) {
                    new Alert(Alert.AlertType.WARNING, "Course not found for ID: " + addToCart.getCourseId()).show();
                    return;
                }
                courseDTOS.add(courseDTO);



                // Search Register
              /*  RegisterDTO registerDTO = registerBo.searchRegister(addToCart.getCourseId());

                // Use your search method here
                if (registerDTO == null) {
                    new Alert(Alert.AlertType.WARNING, "Register not found for ID: " + registerDTO.getRegisterId()).show();
                    return;
                }

                // Populate RegisterDTO with updated details
                Register register = new Register(
                        registerDTO.getRegisterId(),
                        registerDTO.getRegisterDate(),
                        registerDTO.getExpiredDate(),
                        registerDTO.getStudentId(),
                        registerDTO.getCourseId()
                );*/

                // Capture the first RegisterDTO for PaymentDTO
//                if (register == null) {
//                    firstRegisterDTO = register;
//                }
            }

            PaymentDTO paymentDTO = new PaymentDTO(
                    purchaseId,
                    purchaseDate,
                    totalAmount,
                    paymentAmount,
                    balanceAmount,
                    null
            );

            boolean isTransactionSuccessful = registerBo.addTransaction(paymentDTO, registerDTOS, courseDTOS);

            if (isTransactionSuccessful) {
                new Alert(Alert.AlertType.INFORMATION, "Purchase completed successfully!").show();
                addToCartList.clear();
                tblAddToCart.refresh();
                clearInputFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Purchase failed! Please try again.").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        cmbSelectCourse.setPromptText("Select a course");
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
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        List<RegisterDTO> registerDTOS = new ArrayList<>();
        User user = new User();
        try {
            if (txtStudentNIC.getText().isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Please enter a valid Student NIC!").show();
                return;
            }

            StudentDTO studentDTO = studentBo.searchStudentByNic(txtStudentNIC.getText());
            if (studentDTO == null) {
                new Alert(Alert.AlertType.WARNING, "Student not found for the given NIC!").show();
                return;
            }

            Student student = new Student(
                    studentDTO.getId(),
                    studentDTO.getName(),
                    studentDTO.getPhoneNo(),
                    studentDTO.getNic(),
                    studentDTO.getGmail(),
                    studentDTO.getGender(),
                    studentDTO.getAddress(),
                    user

            );
            for (AddToCartTM addToCart : addToCartList) {
                String registerId = registerBo.generateNextRegisterId();

                CourseDTO courseDTO = courseBo.searchCourse(addToCart.getCourseId());
                if (courseDTO == null) {
                    new Alert(Alert.AlertType.WARNING, "Course not found for ID: " + addToCart.getCourseId()).show();
                    return;
                }
                Course  course = new Course(
                        courseDTO.getCourseId(),
                        courseDTO.getCourseName(),
                        courseDTO.getCourseSeats(),
                        courseDTO.getCourseDescription(),
                        courseDTO.getCourseDuration(),
                        courseDTO.getCourseFee()
                        );

                RegisterDTO registerDTO = new RegisterDTO(
                        registerId,
                        addToCart.getRegisterDate(),
                        addToCart.getExpiredDate(),
                        student,
                        course
                );
                registerDTOS.add(registerDTO);
            }

            boolean isRegistered = registerBo.addRegisterDetails(registerDTOS);
            if (isRegistered) {
                new Alert(Alert.AlertType.INFORMATION, "Registration Successful!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to register courses!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }


    private void calculateCustomerPaymentBalanceAmount() {
        String paymentText = txtCustomerPaymentAmount.getText();
        String totalText = txtTotalAmount.getText();

        if (!paymentText.isEmpty() && !totalText.isEmpty()) {
            double payment = Double.parseDouble(paymentText);
            double total = Double.parseDouble(totalText);
            double balance = payment - total;

            txtCustomerPaymentBalance.setText(String.format("%.2f", balance));
        }
    }

    @FXML
    void txtPaymentAmountOnAction(KeyEvent event) {
        try {
            String paymentText = txtCustomerPaymentAmount.getText();
            String totalText = txtTotalAmount.getText();

            if (!paymentText.isEmpty() && !totalText.isEmpty()) {
                double payment = Double.parseDouble(paymentText);
                double total = Double.parseDouble(totalText);
                double balance = payment - total;

                txtCustomerPaymentBalance.setText(String.format("%.2f", balance));
                if (balance >= 0) {
                    txtCustomerPaymentAmount.setStyle("-fx-text-fill: black; -fx-font-family: Impact;");
                    btnBuyCourse.setDisable(false);
                } else {
                    txtCustomerPaymentAmount.setStyle("-fx-font-family: Impact;-fx-text-fill: red;");
                    btnBuyCourse.setDisable(true);
                }
            } else {
                txtCustomerPaymentBalance.setText("");
                txtCustomerPaymentAmount.setStyle("-fx-font-family: Impact;-fx-text-fill: red;");
                btnBuyCourse.setDisable(true);
            }
        } catch (NumberFormatException e) {
            txtCustomerPaymentBalance.setText("");
            txtCustomerPaymentAmount.setStyle("-fx-text-fill: red;");
            btnBuyCourse.setDisable(true);
        }
    }

    @FXML
    void txtAdvancePaymentTypedOnAction(KeyEvent event) {
        try {
            String advancePaymentText = txtAdvancePayment.getText();
            String courseFeeText = txtCourseFee.getText();

            if (!advancePaymentText.isEmpty() && !courseFeeText.isEmpty()) {
                double advancePayment = Double.parseDouble(advancePaymentText);
                double courseFee = Double.parseDouble(courseFeeText);
                double balance = courseFee - advancePayment;

                txtCourseFeeBalance.setText(String.format("%.2f", balance));
            } else {
                txtCourseFeeBalance.setText("");
            }
        } catch (NumberFormatException e) {
            txtCourseFeeBalance.setText("");
        }
    }

    @FXML
    public void txtStudentNICOnAction(ActionEvent actionEvent) {
        btnSearch.requestFocus();
    }

    private void clearInputFields() {
        cmbSelectCourse.setValue(null);
        txtCourseId.clear();
        txtCourseFee.clear();
        txtCourseDuration.clear();
        txtAvailableSeats.clear();
        txtAdvancePayment.clear();
        txtCourseFeeBalance.clear();
        txtPurchaseId.requestFocus();
    }

    private void calculateTotalAmount() {
        double total = 0.0;
        for (AddToCartTM course : addToCartList) {
            total += course.getAdvanceAmount();
        }
        txtTotalAmount.setText(String.format("%.2f", total));
    }
}
