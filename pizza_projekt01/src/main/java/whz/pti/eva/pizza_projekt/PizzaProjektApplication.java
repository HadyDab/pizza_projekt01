package whz.pti.eva.pizza_projekt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaProjektApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaProjektApplication.class, args);
	}

	@Bean
	CommandLineRunner init(){ return (evt) -> {
		System.out.println("Hello Welt");};}


}






