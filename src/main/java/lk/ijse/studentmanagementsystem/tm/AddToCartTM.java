package lk.ijse.studentmanagementsystem.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddToCartTM {
    private String purchaseId;
    private String studentId;
    private String courseId;
    private String courseFee;
    private double advanceAmount;
    private double balanceAmount;
    private String registerDate;
    private JFXButton Remove;
}
