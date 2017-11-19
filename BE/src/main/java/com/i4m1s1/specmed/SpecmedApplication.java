package com.i4m1s1.specmed;

import com.i4m1s1.specmed.Model.Employee;
import com.i4m1s1.specmed.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpecmedApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpecmedApplication.class, args);
	}
}
