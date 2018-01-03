package com.i4m1s1.specmed.service.prescription;

import com.i4m1s1.specmed.core.SMException;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.core.dict.WarningMsg;
import com.i4m1s1.specmed.core.dict.persistence.DictionarySM;
import com.i4m1s1.specmed.core.helper.AuthHelper;
import com.i4m1s1.specmed.dto.PrescriptionDTO;
import com.i4m1s1.specmed.persistence.Customer;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Prescription;
import com.i4m1s1.specmed.persistence.User;
import com.i4m1s1.specmed.repository.CustomerRepository;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.PrescriptionRepository;
import com.i4m1s1.specmed.service.common.catchs.BasicServiceCatch;
import com.i4m1s1.specmed.service.common.request.BasicRequest;
import com.i4m1s1.specmed.service.common.response.BasicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Serwis udostępniający zapis recepty w systemie
 * Created by br33 on 05.12.2017.
 */
@Service
public class ProviderSavePrescriptionService extends BasicServiceCatch<PrescriptionDTO, Prescription> {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;

    @Autowired
    HttpServletRequest httpRequest;

    /**
     * Metoda usługowa do wystawiania recept
     *
     * @param request żądanie zawierające dane recept {@link PrescriptionRepository}
     * @return Lista danych o recepcie
     * @throws SMException
     */
    @Override
    protected BasicResponse<Prescription> provide(BasicRequest<PrescriptionDTO> request) throws SMException {
        PrescriptionDTO requestPrescription = request.getChunkData();
        Prescription prescriptionToSave = new Prescription();
        User currentUser = AuthHelper.readToken(httpRequest.getHeader(AuthHelper.JWT_HEADER));

        if (currentUser == null || currentUser.getEntityId() == null) {
            throw new SMException("54466522125858", WarningMsg.MUST_LOGGED);
        }

        prescriptionToSave.setNFZunit(requestPrescription.getNFZunit());
        prescriptionToSave.setCreationDate(requestPrescription.getCreationDate());
        prescriptionToSave.setValidDate(requestPrescription.getValidDate());
        prescriptionToSave.setDiscount(requestPrescription.getDiscount());
        prescriptionToSave.setRows(requestPrescription.getRows());

        Customer customer = customerRepository.findById(requestPrescription.getCustomerId());
        MedicalEmployee medicalEmployee = medicalEmployeeRepository.findById(currentUser.getEntityId());

        if (medicalEmployee == null || customer == null) {
            throw new SMException("56456243231123", WarningMsg.CANT_BIND_WRONG_DATA);
        }

        prescriptionToSave.setCustomer(customer);
        prescriptionToSave.setMedicalEmployee(medicalEmployee);

        Prescription savedPrescription = prescriptionRepository.save(prescriptionToSave);

        BasicResponse<Prescription> response = new BasicResponse<>();
        response.setContent(savedPrescription);
        return response;
    }

}
