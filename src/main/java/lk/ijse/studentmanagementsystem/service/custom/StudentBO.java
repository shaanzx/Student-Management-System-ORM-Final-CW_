package lk.ijse.studentmanagementsystem.service.custom;

import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.service.SuperBO;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO) throws Exception;

    boolean updateStudent(StudentDTO studentDTO) throws Exception;

    boolean deleteStudent(StudentDTO studentDTO) throws Exception;

    boolean searchStudent(StudentDTO studentDTO) throws Exception;

    boolean getAllStudents(StudentDTO studentDTO) throws Exception;

    boolean loadIds(String id) throws Exception;
}
