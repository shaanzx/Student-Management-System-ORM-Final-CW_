package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.dao.custom.Student_CourseDAO;
import lk.ijse.studentmanagementsystem.entity.Student_Course;

import java.util.ArrayList;

public class Student_CourseDAOImpl implements Student_CourseDAO {
    @Override
    public String getLastId() throws Exception {
        return "";
    }

    @Override
    public boolean save(Student_Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Student_Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Student_Course search(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Student_Course> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }
}
