package com.i4m1s1.specmed.service.response;

import com.i4m1s1.specmed.dto.DoctorBasicDataDTO;

import java.util.List;

/**
 * @author Tobiasz Fortaszewski <t.fortaszewski@gmail.com>
 */
public class ProviderDoctorBasicDataResponse implements ServiceResponse{

    private List<DoctorBasicDataDTO> doctorBasicDataDTOList;

    public ProviderDoctorBasicDataResponse (List<DoctorBasicDataDTO> doctorBasicDataDTOList) {
        this.doctorBasicDataDTOList = doctorBasicDataDTOList;
    }

    public List<DoctorBasicDataDTO> getDoctorBasicDataDTOList() {
        return doctorBasicDataDTOList;
    }

    public void setDoctorBasicDataDTOList(List<DoctorBasicDataDTO> doctorBasicDataDTOList) {
        this.doctorBasicDataDTOList = doctorBasicDataDTOList;
    }
}
