package lk.ijse.studentmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_seats")
    private int courseSeats;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "course_duration")
    private String courseDuration;

    @Column(name = "course_fee")
    private double courseFee;

}
