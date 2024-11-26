package lk.ijse.studentmanagementsystem.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseTM {
    private String courseId;
    private String name;
    private int seats;
    private String description;
    private String duration;
    private String fee;
}
