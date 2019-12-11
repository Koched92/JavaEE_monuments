package m2.proj.celebrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import m2.proj.celebrite.entities.Monument;
import m2.proj.celebrite.metier.ProjMetier;

@SpringBootApplication
public class ProjetMonumentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetMonumentsApplication.class, args);
		
	}
} 