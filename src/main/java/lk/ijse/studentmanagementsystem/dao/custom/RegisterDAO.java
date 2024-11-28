package lk.ijse.studentmanagementsystem.dao.custom;

import lk.ijse.studentmanagementsystem.dao.CrudDAO;
import lk.ijse.studentmanagementsystem.dto.PaymentDTO;
import lk.ijse.studentmanagementsystem.dto.RegisterDTO;
import lk.ijse.studentmanagementsystem.entity.Register;
import org.hibernate.Session;

import java.util.List;

public interface RegisterDAO extends CrudDAO<Register, String> {

    boolean saveRegister(List<Register> registers, Session session) throws Exception;

    Register searchByCourseId(String courseId, Session session) throws Exception;
}
