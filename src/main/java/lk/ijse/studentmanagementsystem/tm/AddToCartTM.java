package lk.ijse.studentmanagementsystem.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddToCartTM {
    private String purchaseId;
    private String studentId;
    private String courseId;
    private double courseFee;
    private double advanceAmount;
    private double balanceAmount;
    private Date registerDate;
    private Date expiredDate;
    private JFXButton Remove;
}
