package lk.ijse.studentmanagementsystem.dto;

import lk.ijse.studentmanagementsystem.entity.Register;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
      private String paymentId;
      private Date paymentDate;
      private double paymentAmount;
      private double advanceAmount;
      private double balanceAmount;
      private Register registerId;
}
