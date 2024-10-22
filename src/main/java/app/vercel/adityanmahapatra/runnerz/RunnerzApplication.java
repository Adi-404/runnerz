package app.vercel.adityanmahapatra.runnerz;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.slf4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import app.vercel.adityanmahapatra.runnerz.run.jdbcCLientRunRepository;
import app.vercel.adityanmahapatra.runnerz.run.location;
import app.vercel.adityanmahapatra.runnerz.run.Run;

@SpringBootApplication
public class RunnerzApplication {

	private static final Logger log = LoggerFactory.getLogger(RunnerzApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunnerzApplication.class, args);
		
	}

	// @Bean
	// CommandLineRunner runner(RunRepository runRepository) {
	// 	return args -> {
	// 		run run = new run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plus(1,ChronoUnit.HOURS),5,location.OUTDOOR);
	// 		runRepository.create(run);

	// 	};

	// }

}
