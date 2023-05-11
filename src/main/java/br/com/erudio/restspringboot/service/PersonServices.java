package br.com.erudio.restspringboot.service;

import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(String id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Jade");
        person.setLastName("Cardoso");
        person.setGender("Female");
        person.setAddress("Qnl");

        return person;
    }

    public List<Person> findAll(){
        logger.info("Finding all people...");
        return personRepository.findAll();
    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        return person;
    }

    public Person update(Person person) {

        logger.info("Updating one person!");

        return person;
    }

    public void delete(String id) {

        logger.info("Deleting one person!");
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName(i + " - Firstname ");
        person.setLastName("Lastname");
        person.setAddress("Some address in Brazil ");
        person.setGender("Male");

        return person;
    }


}
