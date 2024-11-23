package lk.ijse.studentmanagementsystem.service.custom;

import lk.ijse.studentmanagementsystem.dto.StudentDTO;
import lk.ijse.studentmanagementsystem.dto.UserDTO;
import lk.ijse.studentmanagementsystem.service.SuperBO;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO userDTO) throws Exception;
}
