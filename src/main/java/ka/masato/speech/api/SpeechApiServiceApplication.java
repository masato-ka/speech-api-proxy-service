package ka.masato.speech.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpeechApiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeechApiServiceApplication.class, args);
	}
}
