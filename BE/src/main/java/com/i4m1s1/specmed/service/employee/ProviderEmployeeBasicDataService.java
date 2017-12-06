package com.i4m1s1.specmed.service.employee;

import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.service.catchs.ListServiceCatch;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by br33 on 25.11.2017.
 */
@Service
public class ProviderEmployeeBasicDataService extends ListServiceCatch<Employee, Employee> {

    @Autowired
    private EmployeeRepository repository;

    public ListResponse<Employee> provide(ListRequest<Employee> request) {
        Employee employee = request.getSearchCriteria();
        Example<Employee> exampleEmployee = Example.of(employee);

        // Jako pierwszy parametr przekazujemy egzemplarz do wyszukiwania, jako drugi parametr obiekt rozszerzający Pageable (w tym przypadku PageRequest)
        Page<Employee> page = repository.findAll(exampleEmployee, request.getPageCriteria().getAsPageRequest());

        // Ten konstruktor tworzy odpowiedź na podstawie obiektu klasy Page
        return new ListResponse<>(page);
        // Można też tak:

        // long totalCount = page.getTotalElements();
        // long totalPages = page.getTotalPages();
        // List<Employee> employeeList = page.getContent();

        // tutaj można jebnąć jakieś mapowanie do DTO, jeżeli nie chcemy zwracać czystych obiektów z bazy
        // return new ListResponse(totalCount, totalPages, employeeList);
    }
}
