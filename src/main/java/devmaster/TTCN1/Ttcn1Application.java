package devmaster.TTCN1;

import devmaster.TTCN1.service.UploadFileService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ttcn1Application {

	public static void main(String[] args) {
		SpringApplication.run(Ttcn1Application.class, args);
	}

	@Bean
	CommandLineRunner init(UploadFileService uploadFileService) {
		return (args) -> {

			uploadFileService.init();
		};
	}

}
