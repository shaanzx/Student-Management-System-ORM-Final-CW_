package lk.ijse.studentmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDTO {
      private String courseId;
      private String courseName;
      private int courseSeats;
      private String courseDescription;
      private String courseDuration;
      private double courseFee;
}
