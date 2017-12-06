package com.i4m1s1.specmed.service.insurance;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.Insurance;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.InsuranceRepository;
import com.i4m1s1.specmed.service.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Serwis udostępniający pobieranie wszystkich danych ubezpieczeń
 * Created by br33 on 02.12.2017.
 */
@Service
public class ProviderGetInsuranceListService extends ListServiceCatch<Insurance, Insurance> {

    @Autowired
    private InsuranceRepository repository;

    /**
     * Metoda usługowa pobierająca dane wszystkich ubezpieczeń
     * @param request żądanie zawierające dane listy ubezpieczeń {@link InsuranceRepository}
     * @return Lista danych wszystkich ubezpieczeń
     */
    public ListResponse<Insurance> provide(ListRequest<Insurance> request) {
        Insurance insurance = request.getSearchCriteria();
        Example<Insurance> exampleInsurance = Example.of(insurance);

        Page<Insurance> page = repository.findAll(exampleInsurance, request.getPageCriteria().getAsPageRequest());

        return new ListResponse<>(page);
    }
}
