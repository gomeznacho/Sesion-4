package com.example.Sesion4;

import com.example.Sesion4.controllers.HelloController;
import com.example.Sesion4.entities.Laptop;
import com.example.Sesion4.repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Sesion4Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Sesion4Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);
		HelloController saluda = (HelloController) context.getBean(HelloController.class);
		System.out.println(saluda.saludar());


		Laptop laptop1 = new Laptop(null, "Toshiba", "tal", 3.3);
		Laptop laptop2 = new Laptop(null, "Medion", "cual", 5.5);
		Laptop laptop3 = new Laptop(null, "Mac", "pascual", 1.5);

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);


		//System.out.println(repository.findAll().size());

		//repository.deleteById(1L);


	}

}
