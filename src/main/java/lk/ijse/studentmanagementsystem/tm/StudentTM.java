package lk.ijse.studentmanagementsystem.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM {
    private String studentId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String nic;
    private String gender;
}
