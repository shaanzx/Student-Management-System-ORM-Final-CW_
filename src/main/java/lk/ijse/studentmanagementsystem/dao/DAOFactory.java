package lk.ijse.studentmanagementsystem.dao;

import lk.ijse.studentmanagementsystem.dao.custom.daoImpl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    public DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
    USER, STUDENT, COURSE, REGISTER, PAYMENT
    }

    public <T extends SuperDAO> T getDAO(DAOType daoType) {
        switch (daoType) {
            case USER:
                return (T) new UserDAOImpl();
            case STUDENT:
                return (T) new StudentDAOImpl();
            case COURSE:
                return (T) new CourseDAOImpl();
            case REGISTER:
                return (T) new RegisterDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            default:
                return null;
        }
    }
}
