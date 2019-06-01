package me.tatuarvela;

import me.tatuarvela.domain.Student;
import me.tatuarvela.domain.StudentRepository;
import me.tatuarvela.domain.User;
import me.tatuarvela.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringReactApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StudentRepository repository, UserRepository userRepository) {
		return (args) -> {
			repository.save(new Student("Mary", "Poppins", "mary@mail.com"));
			repository.save(new Student("John", "Johnson", "john@mail.com"));
			repository.save(new Student("Mike", "Mitchell", "mike@email.com"));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
