package br.com.erudio.restspringboot.service;

import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.repositories.PersonRepository;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(Long id) {
        logger.info("Finding a person...");
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    }

    public List<Person> findAll() {
        logger.info("Finding all people...");
        return personRepository.findAll();
    }

    public Person create(Person person) {

        logger.info("Creating one person!");

        return personRepository.save(person);
    }

    public Person update(Person person) {

        logger.info("Updating one person!");

        var entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(person);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }

}
