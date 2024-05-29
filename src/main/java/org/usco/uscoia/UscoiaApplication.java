package org.usco.uscoia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "org.usco.uscoia")
@ComponentScan("org.usco.uscoia.*")
public class UscoiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(UscoiaApplication.class, args);
	}

}
