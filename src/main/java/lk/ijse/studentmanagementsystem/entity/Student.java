package lk.ijse.studentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_phone")
    private String studentPhone;

    @Column(name = "student_nic")
    private String studentNic;

    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "student_gender")
    private String studentGender;

    @Column(name = "student_address")
    private String studentAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
