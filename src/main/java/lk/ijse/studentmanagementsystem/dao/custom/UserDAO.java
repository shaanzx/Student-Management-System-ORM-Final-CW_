package lk.ijse.studentmanagementsystem.dao.custom;

import lk.ijse.studentmanagementsystem.dao.CrudDAO;
import lk.ijse.studentmanagementsystem.entity.User;

public interface UserDAO extends CrudDAO<User, String> {

    boolean findByUserName(String userName, String password);

    boolean findByJobId(String adminId);
}
