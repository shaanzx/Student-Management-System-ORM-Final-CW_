package lk.ijse.studentmanagementsystem.dto;

import lk.ijse.studentmanagementsystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String id;
    private String name;
    private String phoneNo;
    private String nic;
    private String gmail;
    private String gender;
    private String address;
    private User user;
}
