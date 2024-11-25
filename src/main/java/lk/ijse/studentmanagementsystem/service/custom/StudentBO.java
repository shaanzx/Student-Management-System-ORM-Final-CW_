package lk.ijse.studentmanagementsystem.service.custom;

import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.service.SuperBO;

import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO) throws Exception;

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    StudentDTO searchStudent(String studentId) throws Exception;

    ArrayList<StudentDTO> getAllStudents() throws Exception;

    boolean loadIds(String id) throws Exception;

    String generateNextStudentId() throws Exception;

    StudentDTO searchStudentByNic(String studentNIC) throws Exception;
}
