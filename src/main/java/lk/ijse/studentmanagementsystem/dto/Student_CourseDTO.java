package lk.ijse.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student_CourseDTO {
    private String studentCourseId;
    private String registerDate;
    private String studentId;
    private String courseId;
}
