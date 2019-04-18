package fr.afcepf.al33;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

//NB: @SpringBootApplication est un équivalent
// de @Configuration + @EnableAutoConfiguration + @ComponentScan/current package
@SpringBootApplication
//@EntityScan( basePackages = {"fr.afcepf.al33.entity"} )
public class MySpringBootApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		//SpringApplication.run(MySpringBootApplication.class, args);
		
		SpringApplication app = new SpringApplication(MySpringBootApplication.class);
		//app.setLogStartupInfo(false);
		app.setAdditionalProfiles("web.dev");
		ConfigurableApplicationContext context = app.run(args);
		
		System.out.println("http://localhost:8080/springBootWebService/index.html");
		
		//securité par défaut si la classe WebSecurityConfig n'existe pas dans l'application:
		//System.out.println("default username=user et password précisé au démarrage");
	}
}
