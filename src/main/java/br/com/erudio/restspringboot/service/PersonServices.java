package br.com.erudio.restspringboot.service;

import br.com.erudio.restspringboot.data.vo.v1.PersonVO;
import br.com.erudio.restspringboot.mapper.DozerMapper;
import br.com.erudio.restspringboot.model.Person;
import br.com.erudio.restspringboot.repositories.PersonRepository;
import exceptions.ResourceNotFoundException;
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

    @Autowired
    DozerMapper dozerMapper;

    public PersonVO findById(Long id) {
        logger.info("Finding a person...");
        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return dozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");
        var entities = personRepository.findAll();
        return dozerMapper.parseListObjects(entities, PersonVO.class);

        //return personRepository.findAll();
    }

    public PersonVO create(PersonVO personVO) {
        logger.info("Creating one person!");
        var entity = dozerMapper.parseObject(personVO, Person.class);
        var vo = dozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;

        //return personRepository.save(personVO);
    }

    public PersonVO update(PersonVO personVO) {

        logger.info("Updating one person!");

        var entity = personRepository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        var vo = dozerMapper.parseObject(personRepository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        personRepository.delete(entity);
    }

}
