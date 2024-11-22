package lk.ijse.studentmanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public Student() {}

    public Student(String studentId, String studentName, String studentPhone, String studentNic, String studentEmail, String studentGender, String studentAddress) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhone = studentPhone;
        this.studentNic = studentNic;
        this.studentEmail = studentEmail;
        this.studentGender = studentGender;
        this.studentAddress = studentAddress;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentNic() {
        return studentNic;
    }

    public void setStudentNic(String studentNic) {
        this.studentNic = studentNic;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", studentNic='" + studentNic + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                '}';
    }
}
