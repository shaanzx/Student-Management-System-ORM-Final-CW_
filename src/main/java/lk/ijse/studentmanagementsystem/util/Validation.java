package lk.ijse.studentmanagementsystem.util;

import javafx.scene.control.TextField;

public class Validation {

    public static final String USER_ID_PATTERN = "^UID-\\d{4}$";  // Example: UID-0001
    public static final String NAME_PATTERN = "^[A-Za-z ]{3,50}$";  // Name: Only letters, 3-50 chars
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";  // Email pattern
    public static final String MOBILE_PATTERN = "^(\\+94|0)[7-9][0-9]{8}$";  // Sri Lankan mobile number
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{6,}$";// Password: Minimum 6 characters, 1 letter, 1 number, 1 symbol (e.g., @, #, $, etc.)


    public static boolean validateField(TextField textField, String pattern) {
        boolean isValid = textField.getText().matches(pattern);
        if (isValid) {
            textField.setStyle("-fx-border-color: green; -fx-border-width: 2;");
        } else {
            textField.setStyle("-fx-border-color: red; -fx-border-width: 2;");
        }
        return isValid;
    }

    public static boolean validateAllFields(TextField userIdField, TextField nameField, TextField emailField, TextField mobileField, TextField passwordField) {
        boolean isUserIdValid = validateField(userIdField, USER_ID_PATTERN);
        boolean isNameValid = validateField(nameField, NAME_PATTERN);
        boolean isEmailValid = validateField(emailField, EMAIL_PATTERN);
        boolean isMobileValid = validateField(mobileField, MOBILE_PATTERN);
        boolean isPasswordValid = validateField(passwordField, PASSWORD_PATTERN);

        return isUserIdValid && isNameValid && isEmailValid && isMobileValid && isPasswordValid;
    }
}
