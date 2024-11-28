package lk.ijse.studentmanagementsystem.dao.custom;

import lk.ijse.studentmanagementsystem.dao.CrudDAO;
import lk.ijse.studentmanagementsystem.entity.Course;

public interface CourseDAO extends CrudDAO<Course,String> {
    boolean isCourseExists(String courseId) throws Exception;

    boolean updateSeats(String courseId) throws Exception;
}
