package lk.ijse.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    String userId;
    String userRole;
    String userEmail;
    String userMobileNo;
    String userName;
    String userPassword;


}
