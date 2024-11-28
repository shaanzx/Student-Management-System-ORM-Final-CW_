package lk.ijse.studentmanagementsystem.service.custom;

import lk.ijse.studentmanagementsystem.dto.CourseDTO;
import lk.ijse.studentmanagementsystem.dto.PaymentDTO;
import lk.ijse.studentmanagementsystem.dto.RegisterDTO;

import java.util.List;

public interface RegisterBO {
   String generateNextRegisterId() throws Exception;

    String generateNextPurchaseId() throws Exception;

    boolean addRegisterDetails(List<RegisterDTO> registerDTOS) throws Exception;

    boolean savePayment(PaymentDTO paymentDTO) throws Exception;

    RegisterDTO searchRegister(String courseId);

    boolean addTransaction(PaymentDTO paymentDTO, List<RegisterDTO> registerDTOS, List<CourseDTO> courseDTOS) throws Exception;
}
