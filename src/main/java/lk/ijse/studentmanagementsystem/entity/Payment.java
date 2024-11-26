package lk.ijse.studentmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @Column(name = "advance_amount")
    private double advanceAmount;

    @Column(name = "balance_amount")
    private double balanceAmount;

    @ManyToOne
    @JoinColumn(name = "student_course_id")
    private Student_Course studentCourse;
}
