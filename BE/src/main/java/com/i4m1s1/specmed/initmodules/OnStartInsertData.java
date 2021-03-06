package com.i4m1s1.specmed.initmodules;

import com.i4m1s1.specmed.core.PersonalData;
import com.i4m1s1.specmed.core.dict.DictionaryNames;
import com.i4m1s1.specmed.core.dict.persistence.DictionarySM;
import com.i4m1s1.specmed.core.helper.DateHelper;
import com.i4m1s1.specmed.persistence.Employee;
import com.i4m1s1.specmed.persistence.MedicalEmployee;
import com.i4m1s1.specmed.persistence.Probe;
import com.i4m1s1.specmed.persistence.Visit;
import com.i4m1s1.specmed.repository.DictionaryRepository;
import com.i4m1s1.specmed.repository.EmployeeRepository;
import com.i4m1s1.specmed.repository.MedicalEmployeeRepository;
import com.i4m1s1.specmed.repository.ProbeRepository;
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
 *
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
    @Autowired
    private ProbeRepository probeRepository;

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
        v1.setDateStart(DateHelper.getCurrentDateAsLong());
        v1.setPlace("222");
        v1.setPrice("219");
        Visit v2 = new Visit();
        v2.setDateStart(DateHelper.getCurrentDateAsLong());
        v2.setPlace("11A");
        v2.setPrice("99");
        Visit v3 = new Visit();
        v3.setDateStart(DateHelper.getCurrentDateAsLong());
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
            if (i == 1) {
                for (Visit v : vl1) {
                    v.setMedicalEmployee(me);
                }
            }
            if (i == 2) {
                for (Visit v : vl2) {
                    v.setMedicalEmployee(me);
                }
            }
            medicalEmployeeRepository.save(me);
        }
        visitRepository.save(vl1);//PONOWNIE BO EDYTOWALISMY COS.
        visitRepository.save(vl2);

        List<MedicalEmployee> list = medicalEmployeeRepository.findAll();
        List<Probe> probes = new ArrayList<>();
        for (MedicalEmployee medicalEmployee : list) {
            Probe probe = new Probe();
            probe.setPrice("123"+medicalEmployee.getId().charAt(medicalEmployee.getId().length()-1));
            probe.setMedicalEmployee(medicalEmployee);
            probe.setComment("Testowy komentarz dla ");
            probe.setDate(DateHelper.getCurrentDateAsLong());
            probe.setType(dictionaryRepository.findByDictionaryName(DictionaryNames.PROBES).getDictMap().get(1));
            probes.add(probe);
        }
        probeRepository.save(probes);
    }

    //repo pracownikow
    public void initEmployees() {
        List<String> names = Arrays.asList("Janusz", "Bogdan", "Zbigniew", "Mateusz");
        List<String> surnames = Arrays.asList("Tracz", "Babacki", "Grzymała", "Przybeusz");
        List<String> pesels = Arrays.asList("98765556777", "12312312312", "98765456788", "87773747273");
        List<PersonalData> pd = new ArrayList<>(4);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int u = 0; u < 4; u++) {
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

        //TYPY ZLECEń MEDYCZNYCH
        Map<Integer, String> mapo = new HashMap<>();
        mapo.put(55, "PROBE");
        mapo.put(24, "MEDICAL_PROCEDURES");

        DictionarySM dicto = new DictionarySM();
        dicto.setDictionaryName(DictionaryNames.ORDER_TYPE);
        dicto.setDictMap(mapo);

        //ODDZIALY NFZ
        Map<Integer, String> mapnfz = new HashMap<>();
        mapnfz.put(1, "Dolnośląski Oddział Narodowego Funduszu Zdrowia we Wrocławiu");
        mapnfz.put(2, "Kujawsko-Pomorski Oddział Narodowego Funduszu Zdrowia w Bydgoszczy");
        mapnfz.put(3, "Lubelski Oddział Narodowego Funduszu Zdrowia w Lublinie");
        mapnfz.put(4, "Lubuski Oddział Narodowego Funduszu Zdrowia w Zielonej Górze");
        mapnfz.put(5, "Łódzki Oddział Narodowego Funduszu Zdrowia w Łodzi");
        mapnfz.put(6, "Małopolski Oddział Narodowego Funduszu Zdrowia w Krakowie");
        mapnfz.put(7, "Mazowiecki Oddział Narodowego Funduszu Zdrowia w Warszawie");
        mapnfz.put(8, "Opolski Oddział Narodowego Funduszu Zdrowia w Opolu");
        mapnfz.put(9, "Podkarpacki Oddział Narodowego Funduszu Zdrowia w Rzeszowie");
        mapnfz.put(10, "Podlaski Oddział Narodowego Funduszu Zdrowia w Białymstoku");
        mapnfz.put(11, "Pomorski Oddział Narodowego Funduszu Zdrowia w Gdańsku");
        mapnfz.put(12, "Śląski Oddział Narodowego Funduszu Zdrowia w Katowicach");
        mapnfz.put(13, "Świętokrzyski Oddział Narodowego Funduszu Zdrowia w Kielcach");
        mapnfz.put(14, "Warmińsko-Mazurski Oddział Narodowego Funduszu Zdrowia w Olsztynie");
        mapnfz.put(15, "Wielkopolski Oddział Narodowego Funduszu Zdrowia w Poznaniu");
        mapnfz.put(16, "Zachodniopomorski Oddział Narodowego Funduszu Zdrowia w Szczecinie");

        DictionarySM dictnfz = new DictionarySM();
        dictnfz.setDictionaryName(DictionaryNames.NFZ_UNIT);
        dictnfz.setDictMap(mapnfz);

        dictionaryRepository.save(dictg);
        dictionaryRepository.save(dict);
        dictionaryRepository.save(dictv);
        dictionaryRepository.save(dicto);
        dictionaryRepository.save(dictnfz);

    }
}
