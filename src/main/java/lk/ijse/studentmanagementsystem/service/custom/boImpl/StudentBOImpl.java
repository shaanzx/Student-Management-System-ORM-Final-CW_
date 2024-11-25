package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.StudentDAO;
import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.entity.Student;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.custom.StudentBO;

import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.STUDENT);
    private User user;

    @Override
    public String generateNextStudentId() throws Exception {
        String lastId = studentDAO.getLastId();
        return incrementStudentId(lastId);
    }

    @Override
    public StudentDTO searchStudentByNic(String studentNIC) throws Exception {
        Student student = studentDAO.searchByNic(studentNIC);
        return new StudentDTO(
                student.getStudentId(),
                student.getStudentName(),
                student.getStudentPhone(),
                student.getStudentNic(),
                student.getStudentEmail(),
                student.getStudentGender(),
                student.getStudentAddress(),
                user
        );
    }

    @Override
    public boolean checkStudent(String studentId) throws Exception {
        return studentDAO.checkStudent(studentId);
    }

    private String incrementStudentId(String lastId) {
        if (lastId == null) {
            return "STU-0001";
        }
        int id = Integer.parseInt(lastId.split("-")[1]);
        id++;
        return String.format("STU-%04d", id);
    }


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
        return studentDAO.update(new Student(
                studentDTO.getId(),
                studentDTO.getName(),
                studentDTO.getPhoneNo(),
                studentDTO.getNic(),
                studentDTO.getGmail(),
                studentDTO.getGender(),
                studentDTO.getAddress(),
                user));
    }

    @Override
    public boolean deleteStudent(String studentId) throws Exception {
        return studentDAO.delete(studentId);
    }

    @Override
    public StudentDTO searchStudent(String studentId) throws Exception {
        Student student = studentDAO.search(studentId);
        return new StudentDTO(
                student.getStudentId(),
                student.getStudentName(),
                student.getStudentPhone(),
                student.getStudentNic(),
                student.getStudentEmail(),
                student.getStudentGender(),
                student.getStudentAddress(),
                user
        );
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        ArrayList<Student> students = studentDAO.getAll();
        for (Student student : students) {
            studentDTOS.add(new StudentDTO(
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getStudentPhone(),
                    student.getStudentNic(),
                    student.getStudentEmail(),
                    student.getStudentGender(),
                    student.getStudentAddress(),
                    user
            ));
        }
        return studentDTOS;
    }

    @Override
    public boolean loadIds(String id) throws Exception {
        return false;
    }


}
