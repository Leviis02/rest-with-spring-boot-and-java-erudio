package br.com.erudio.restspringboot;

import br.com.erudio.restspringboot.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {SpringApplication.run(Startup.class, args);
		Person person = new Person(new AtomicLong().incrementAndGet(),"Levi", "Gonçalves", "Qnl17", "Male");

		System.out.println(person.getFirstName());

	}




}
