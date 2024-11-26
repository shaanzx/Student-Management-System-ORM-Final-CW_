package lk.ijse.studentmanagementsystem.dao.custom.daoImpl;

import lk.ijse.studentmanagementsystem.dao.custom.CourseDAO;
import lk.ijse.studentmanagementsystem.entity.Course;

import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public String getLastId() throws Exception {
        return "";
    }

    @Override
    public boolean save(Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public Course search(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Course> getAll() throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> loadIds() throws Exception {
        return null;
    }
}
