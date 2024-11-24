package lk.ijse.studentmanagementsystem.service.custom.boImpl;

import lk.ijse.studentmanagementsystem.dao.DAOFactory;
import lk.ijse.studentmanagementsystem.dao.custom.UserDAO;
import lk.ijse.studentmanagementsystem.dto.UserDTO;
import lk.ijse.studentmanagementsystem.entity.User;
import lk.ijse.studentmanagementsystem.service.custom.UserBO;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        User user = new User(
                userDTO.getUserId(),
                userDTO.getUserRole(),
                userDTO.getUserEmail(),
                userDTO.getUserMobileNo(),
                userDTO.getUserName(),
                userDTO.getUserPassword(),
                null
        );
        return userDAO.save(user);
    }

    @Override
    public String generateNewUserId() throws Exception {
        String lastId = userDAO.getLastId();
        return incrementUserId(lastId);
    }

    private String incrementUserId(String lastId) {
        if (lastId == null){
            return "UID-0001";
        }
        int id = Integer.parseInt(lastId.split("-")[1]);
        id++;
        return String.format("UID-%04d", id);
    }
}
