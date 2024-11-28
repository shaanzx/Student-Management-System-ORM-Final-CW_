package lk.ijse.studentmanagementsystem.util;

import javafx.scene.control.TextField;

public class Validation {

    public static final String USER_ID_PATTERN = "^UID-\\d{4}$";  // Example: UID-0001
    public static final String STUDENT_ID_PATTERN = "^STU-\\d{4}$"; // Example: SID-0001
    public static final String NAME_PATTERN = "^[A-Za-z ]{3,50}$";  // Name: Only letters, 3-50 chars
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";  // Email pattern
    public static final String MOBILE_PATTERN = "^(\\+94|0)[7-9][0-9]{8}$";  // Sri Lankan mobile number
    public static final String NIC_PATTERN = "^[0-9]{9}[vVxX]$";
    public static final String ADDRESS_PATTERN = "^[A-Za-z0-9 ]{3,50}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{6,}$";// Password: Minimum 6 characters, 1 letter, 1 number, 1 symbol (e.g., @, #, $, etc.)

    public static final String COURSE_ID_PATTERN = "^REG-COOK-\\d{4}-\\d{4}$";
    public static final String COURSE_NAME_PATTERN = "^[A-Za-z ]{3,50}$";
    public static final String COURSE_SEATS_PATTERN = "^\\d{1,3}$";
    public static final String DESCRIPTION_PATTERN = "^[A-Za-z0-9 ]{3,200}$";
    public static final String DURATION_PATTERN = "^\\d{2} Months$";
    public static final String COURSE_FEE_PATTERN = "^\\d{1,8}$";



    public static boolean validateField(TextField textField , String pattern) {
        boolean isValid = textField.getText().matches(pattern);
        if (isValid) {
            textField.setStyle("-fx-border-color: green; -fx-border-width: 2;-fx-font-size: 14px;");
        } else {
            textField.setStyle("-fx-border-color: red; -fx-border-width: 2; -fx-font-size: 14px;");
        }
        return isValid;
    }

    //User Validation
    public static boolean validateAllFields(TextField userIdField, TextField nameField, TextField emailField, TextField mobileField, TextField passwordField) {
        boolean isUserIdValid = validateField(userIdField, USER_ID_PATTERN);
        boolean isNameValid = validateField(nameField, NAME_PATTERN);
        boolean isEmailValid = validateField(emailField, EMAIL_PATTERN);
        boolean isMobileValid = validateField(mobileField, MOBILE_PATTERN);
        boolean isPasswordValid = validateField(passwordField, PASSWORD_PATTERN);

        return isUserIdValid && isNameValid && isEmailValid && isMobileValid && isPasswordValid;
    }

    //Student Validation
    public static boolean validateAllFields(TextField studentIdField, TextField nameField, TextField emailField, TextField mobileField, TextField nicField, TextField addressField) {
        boolean isStudentIdValid = validateField(studentIdField, STUDENT_ID_PATTERN);
        boolean isNameValid = validateField(nameField, NAME_PATTERN);
        boolean isEmailValid = validateField(emailField, EMAIL_PATTERN);
        boolean isMobileValid = validateField(mobileField, MOBILE_PATTERN);
        boolean isNicValid = validateField(nicField, NIC_PATTERN);
        boolean isAddressValid = validateField(addressField, ADDRESS_PATTERN);

        return isStudentIdValid && isNameValid && isEmailValid && isMobileValid && isNicValid && isAddressValid;
    }

    //Login Validation
    public static boolean validateAllFields(TextField userNameField, TextField passwordField) {
        boolean isUserNameValid = validateField(userNameField, USER_ID_PATTERN);
        boolean isPasswordValid = validateField(passwordField, PASSWORD_PATTERN);

        return isUserNameValid && isPasswordValid;
    }

    //Course Validation
    public static boolean validateCourseFields(TextField courseIdField, TextField courseNameField, TextField totalSeatsField, TextField descriptionField, TextField durationField, TextField feeField) {
        boolean isCourseIdValid = validateField(courseIdField, COURSE_ID_PATTERN);
        boolean isCourseNameValid = validateField(courseNameField, COURSE_NAME_PATTERN);
        boolean isTotalSeatsValid = validateField(totalSeatsField, COURSE_SEATS_PATTERN);
        boolean isDescriptionValid = validateField(descriptionField, DESCRIPTION_PATTERN);
        boolean isDurationValid = validateField(durationField, DURATION_PATTERN);
        boolean isFeeValid = validateField(feeField, COURSE_FEE_PATTERN);

        return isCourseIdValid && isCourseNameValid && isTotalSeatsValid && isDescriptionValid && isDurationValid && isFeeValid;
    }
}
