package com.i4m1s1.specmed.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.service.request.ListRequest;
import com.i4m1s1.specmed.service.response.common.ListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by br33 on 25.11.2017.
 */
@Service
public class ProviderEmployeeBasicDataService extends ServiceCatch<ListResponse, ListRequest> {

    @Autowired
    private EmployeeRepository repository;

    public ListResponse provide(ListRequest request) {
        // TODO: Wymyślić fajniejszą metodę ustawiania kretyriów np. wyszukiwanie w obiekcie np. przekazujemy personalData.surname i samo mapuje
        PersonalData personalData = new PersonalData();

        JsonNode nameParameter = request.getSearchCriteria().get("name");
        if (nameParameter != null && nameParameter.isTextual()) {
            personalData.setName(nameParameter.asText());
        }
        JsonNode surnameParameter = request.getSearchCriteria().get("surname");
        if (surnameParameter != null && surnameParameter.isTextual()) {
            personalData.setSurname(surnameParameter.asText());
        }

        // Tworzymy "przykładowy" obiekt, czyli taki do którego będziemy porównywać!
        Example<Employee> exampleEmployee = Example.of(new Employee(personalData));

        // Jako pierwszy parametr przekazujemy egzemplarz do wyszukiwania, jako drugi parametr obiekt rozszerzający Pageable (w tym przypadku PageRequest)
        Page page = repository.findAll(exampleEmployee, request.getPageCriteria().getAsPageRequest());

        // Ten konstruktor tworzy odpowiedź na podstawie obiektu klasy Page
        return new ListResponse(page);

        // Można też tak:

        // long totalCount = page.getTotalElements();
        // long totalPages = page.getTotalPages();
        // List<Employee> employeeList = page.getContent();

        // tutaj można jebnąć jakieś mapowanie do DTO, jeżeli nie chcemy zwracać czystych obiektów z bazy

        // return new ListResponse(totalCount, totalPages, employeeList);
    }
}
