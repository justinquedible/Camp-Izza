package com.campizza.backend.campizzabackend;

import com.campizza.backend.campizzabackend.data.AdminRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.io.File;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AdminRepository.class)

public class CampizzaBackendApplication{

	//File myFile = new File("rugged-sunbeam-229808-97e15ab7c19b.json");

	//private String pathName = this.getClass().getClassLoader().getResource("rugged-sunbeam-229808-97e15ab7c19b.json").toExternalForm();

	public static void main(String[] args) {
		//System.out.println(pathName);

		SpringApplication.run(CampizzaBackendApplication.class, args);
	}
}
