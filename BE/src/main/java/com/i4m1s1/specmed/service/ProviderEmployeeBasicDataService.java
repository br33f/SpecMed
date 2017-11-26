package com.i4m1s1.specmed.service;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.annotation.ParamMap;
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
@ParamMap(name = {"name", "surname"})//todo moze podawac tu jeszcze typy? np. {name, String.class}, {time, Long.class}
@Service
public class ProviderEmployeeBasicDataService extends ServiceCatch<ListResponse<Employee>, ListRequest> {

    @Autowired
    private EmployeeRepository repository;

    public ListResponse<Employee> provide(ListRequest request) {

        //TODO: postaram sie zrobic tak, żeby np. default to był string
        //todo i żeby nie trzeba było wyciągać w ten sposób tylko od razu brac z mapy albo metodą jakąś
        String name = params.get("name").asText();
        String surname = params.get("surname").asText();
        // TODO: Wymyślić fajniejszą metodę ustawiania kretyriów np. wyszukiwanie w obiekcie np. przekazujemy personalData.surname i samo mapuje
        PersonalData personalData = new PersonalData();

        //TODO: sprawdzanie czy jest textual postaram sie tez jakos wywalic do generica
        personalData.setName(name);
        personalData.setSurname(surname);

        // Tworzymy "przykładowy" obiekt, czyli taki do którego będziemy porównywać!
        Example<Employee> exampleEmployee = Example.of(new Employee(personalData));

        // Jako pierwszy parametr przekazujemy egzemplarz do wyszukiwania, jako drugi parametr obiekt rozszerzający Pageable (w tym przypadku PageRequest)
        Page<Employee> page = repository.findAll(exampleEmployee, request.getPageCriteria().getAsPageRequest());

        // Ten konstruktor tworzy odpowiedź na podstawie obiektu klasy Page
        return new ListResponse<>(page);

        // Można też tak:

        // long totalCount = page.getTotalElements();
        // long totalPages = page.getTotalPages();
        // List<Employee> employeeList = page.getContent();

        // tutaj można jebnąć jakieś mapowanie do DTO, jeżeli nie chcemy zwracać czystych obiektów z bazy
        // ja bym w response dawal jak najmniej zagniezdzen, czyli bez content.getPersonalData().getBirthday().getDay...

        // return new ListResponse(totalCount, totalPages, employeeList);
    }
}
