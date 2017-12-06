package com.i4m1s1.specmed.initmodules;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.core.dict.persistence.DictionarySM;
import com.i4m1s1.specmed.core.helper.DateHelper;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Init moduł do ładowania danych do MongoDB - wykorzystywany podczas developu i testów
 * @author Tobiasz Fortaszewski t.fortaszewski@gmail.com
 */
@Component
public class OnStartInsertData {

    @Autowired
    private MedicalEmployeeRepository medicalEmployeeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * Czysci baze zeby odpalic ponownie inity.
     * Potrzebne czasami bo mamy referencje w bazie i kilkukrotne odpalenie initow bez czyszczenia
     * mogloby referencje zdewaluowac.
     */
    public void destroyAllModules() {
        visitRepository.deleteAll();
        employeeRepository.deleteAll();
        dictionaryRepository.deleteAll();
        medicalEmployeeRepository.deleteAll();
    }

    //repo lekarzy
    public void initMedicalEmpoyeeAndVisit() {
        //todo ZROBIC TO JAK CZLOWIEK NIE JAK ZWIERZE
        //todo zadanie dla kogos ambitnego
        List<String> names = Arrays.asList("Roman", "Tomasz", "Zbigniew", "Mateusz");
        List<String> surnames = Arrays.asList("Abacki", "Babacki", "Cabacki", "Pawulonik");
        List<String> pesels = Arrays.asList("98765556777", "12312312312", "98765456788", "87773747273");
        List<List<String>> specs = Arrays.asList(
                Arrays.asList("ONKOLOG"),
                Arrays.asList("ONKOLOG", "GINEKOLOG"),
                Arrays.asList("PEDIATRA", "ONKOLOG"),
                Arrays.asList("PEDIATRA")
        );
        List<PersonalData> pd = new ArrayList<>(4);


        //wizyty
        Visit v1 = new Visit();
        v1.setDate(DateHelper.getCurrentDateAsLong());
        v1.setPlace("222");
        v1.setPrice("219");
        Visit v2 = new Visit();
        v2.setDate(DateHelper.getCurrentDateAsLong());
        v2.setPlace("11A");
        v2.setPrice("99");
        Visit v3 = new Visit();
        v3.setDate(DateHelper.getCurrentDateAsLong());
        v3.setPlace("121A");
        v3.setPrice("52");
        List<Visit> vl1 = Arrays.asList(v1, v2);
        List<Visit> vl2 = Arrays.asList(v3);


//        visitRepository.save(vl1);
//        visitRepository.save(vl2);

        for (int i = 0; i < 4; i++) {
            pd.add(new PersonalData());
            PersonalData personalData = pd.get(i);
            personalData.setBirthday(new Date());
            personalData.setPesel(pesels.get(pd.indexOf(personalData)));
            personalData.setSurname(surnames.get(pd.indexOf(personalData)));
            personalData.setName(names.get(pd.indexOf(personalData)));
            personalData.setGender("FEMALE");

            MedicalEmployee me = new MedicalEmployee();
            me.setPersonalData(personalData);
            me.setSpecializationList(specs.get(pd.indexOf(personalData)));

            //xD wow wow
            if(i == 1){
//                me.setVisits(vl1);
                for(Visit v : vl1) {
                    v.setMedicalEmpoyee(me);
                }
            }
            if(i ==2 ){
//                me.setVisits(vl2);
                for(Visit v : vl2) {
                    v.setMedicalEmpoyee(me);
                }
            }
            medicalEmployeeRepository.save(me);
        }
        visitRepository.save(vl1);//PONOWNIE BO EDYTOWALISMY COS.
        visitRepository.save(vl2);
    }

    //repo pracownikow
    public void initEmployees() {
        List<String> names = Arrays.asList("Janusz", "Bogdan", "Zbigniew", "Mateusz");
        List<String> surnames = Arrays.asList("Tracz", "Babacki", "Grzymała", "Przybeusz");
        List<String> pesels = Arrays.asList("98765556777", "12312312312", "98765456788", "87773747273");
        List<PersonalData> pd = new ArrayList<>(4);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int u = 0; u < 4; u++ ) {
                    pd.add(new PersonalData());
                    PersonalData personalData = pd.get(i);
                    personalData.setBirthday(new Date());
                    personalData.setPesel(pesels.get(i));
                    personalData.setSurname(surnames.get(j));
                    personalData.setName(names.get(u));
                    personalData.setGender("MALE");

                    Employee employee = new Employee();
                    employee.setPersonalData(personalData);

                    employeeRepository.save(employee);
                }
            }
        }
    }

    public void zapelnijSlowniki() {
        //SPECIALIZACJE
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "ONKOLOG");
        map.put(2, "PEDIATRA");
        map.put(3, "KARDIOLOG");

        DictionarySM dict = new DictionarySM();
        dict.setDictionaryName(DictionaryNames.SPECIALIZATION);
        dict.setDictMap(map);

        //STATUS WIZYTY
        Map<Integer, String> mapv = new HashMap<>();
        mapv.put(1, "WOLNA");
        mapv.put(2, "ZAJETA");
        mapv.put(3, "OPLACONA");

        DictionarySM dictv = new DictionarySM();
        dictv.setDictionaryName(DictionaryNames.VISIT_STATUS);
        dictv.setDictMap(mapv);

        //PLCIE
        Map<Integer, String> mapg = new HashMap<>();
        mapg.put(1, "MALE");
        mapg.put(2, "FEMALE");
        mapg.put(3, "UNSPECIFIED");

        DictionarySM dictg = new DictionarySM();
        dictg.setDictionaryName(DictionaryNames.GENDER);
        dictg.setDictMap(mapv);

        dictionaryRepository.save(dictg);
        dictionaryRepository.save(dict);
        dictionaryRepository.save(dictv);
    }
}
