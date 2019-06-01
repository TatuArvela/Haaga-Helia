package io.github.tatuarvela;

import io.github.tatuarvela.domain.Book;
import io.github.tatuarvela.domain.BookRepository;
import io.github.tatuarvela.domain.User;
import io.github.tatuarvela.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadBooks(BookRepository bookRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book("A Farewell to Arms", "Ernest Hemingway", 1929, "1232323-21", 20));
			bookRepository.save(new Book("Animal Farm", "George Orwell", 1945, "2212343-5", 20));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
