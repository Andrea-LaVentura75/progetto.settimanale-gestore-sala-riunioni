package it.epicode.salaRiunioni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SalaRiunioniApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SalaRiunioniApplication.class, args);

		// Recupera il MenuRunner dal contesto Spring
		it.epicode.salaRiunioni.runner.MenuRunner menuRunner = context.getBean(it.epicode.salaRiunioni.runner.MenuRunner.class);
		menuRunner.start();
	}
}
