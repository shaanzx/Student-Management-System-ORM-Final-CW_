package lk.ijse.studentmanagementsystem.service.custom;

import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.service.SuperBO;

import java.util.ArrayList;

public interface CourseBO extends SuperBO {
    boolean saveCourse(CourseDTO courseDTO) throws Exception;

    boolean updateCourse(CourseDTO courseDTO) throws Exception;

    boolean deleteCourse(String  courseId) throws Exception;

    CourseDTO searchCourse(String courseId) throws Exception;

    ArrayList<CourseDTO> getAllCourses() throws Exception;

    boolean loadIds(String id) throws Exception;

    String generateNextCourseId() throws Exception;
}
