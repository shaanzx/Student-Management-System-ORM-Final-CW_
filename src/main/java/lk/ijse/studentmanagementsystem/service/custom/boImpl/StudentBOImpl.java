package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.StudentDAO;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.entity.Student;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    private User user;
    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
        Student student = new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getPhoneNo(),
                studentDTO.getNic(),
                studentDTO.getGmail(),
                studentDTO.getGender(),
                studentDTO.getAddress(),
                user
        );
        return studentDAO.save(student);
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean deleteStudent(StudentDTO studentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean searchStudent(StudentDTO studentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean getAllStudents(StudentDTO studentDTO) throws Exception {
        return false;
    }

    @Override
    public boolean loadIds(String id) throws Exception {
        return false;
    }
}
