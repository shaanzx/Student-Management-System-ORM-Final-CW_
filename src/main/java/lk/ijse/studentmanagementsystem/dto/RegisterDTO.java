package lk.ijse.studentmanagementsystem.dto;

import lk.ijse.studentmanagementsystem.entity.Course;
import lk.ijse.studentmanagementsystem.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterDTO {
    private String registerId;
    private Date registerDate;
    private Date expiredDate;
    private Student studentId;
    private Course courseId;

}
