package lk.ijse.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
      private String paymentId;
      private String paymentDate;
      private double paymentAmount;
      private double advanceAmount;
      private double balanceAmount;
      private String studentCourseId;
}
