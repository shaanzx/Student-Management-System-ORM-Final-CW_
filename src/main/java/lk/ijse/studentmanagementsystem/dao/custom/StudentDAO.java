package lk.ijse.studentmanagementsystem.dao.custom;

import lk.ijse.studentmanagementsystem.dao.CrudDAO;
import lk.ijse.studentmanagementsystem.entity.Student;

import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student,String> {

    Student searchByNic(String studentNIC) throws Exception;
}
