package lk.ijse.studentmanagementsystem.service.custom;

import lk.ijse.studentmanagementsystem.dto.PaymentDTO;
import lk.ijse.studentmanagementsystem.dto.RegisterDTO;

import java.util.List;

public interface RegisterBO {
    String generateNextStudentCourseId() throws Exception;

    boolean savePayment(PaymentDTO paymentDTO) throws Exception;

    String generateNextPurchaseId() throws Exception;

    boolean saveRegister(RegisterDTO registerDTO) throws Exception;

    boolean updateCourseSeats(String courseId) throws Exception;

    boolean addRegisterCourseDetails(List<RegisterDTO> registerDTOS, PaymentDTO paymentDTO, String courseId);
}
