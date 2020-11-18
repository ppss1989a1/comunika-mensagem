package br.com.assertiva.comunika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = { "br.com.assertiva.comunika.repository" })
public class ComunikaMensagemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunikaMensagemApplication.class, args);
	}

}
