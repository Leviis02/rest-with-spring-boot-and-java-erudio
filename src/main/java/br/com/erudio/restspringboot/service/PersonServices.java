package br.com.erudio.restspringboot.service;

import br.com.erudio.restspringboot.data.vo.v1.PersonVO;
import br.com.erudio.restspringboot.data.vo.v2.PersonVOV2;
import br.com.erudio.restspringboot.mapper.DozerMapper;
import br.com.erudio.restspringboot.mapper.custom.PersonMapper;
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
    PersonRepository repository;

    @Autowired
    DozerMapper dozerMapper;

    @Autowired
    PersonMapper personMapper;

    public PersonVO findById(Long id) {
        logger.info("Finding a person...");
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return dozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        logger.info("Finding all people...");
        var entities = repository.findAll();
        return dozerMapper.parseListObjects(entities, PersonVO.class);

        //return personRepository.findAll();
    }

    public PersonVO createV1(PersonVO personVO) {
        logger.info("Creating one person!");
        var entity = dozerMapper.parseObject(personVO, Person.class);
        var vo = dozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;

        //return personRepository.save(personVO);
    }
    public PersonVOV2 createV2(PersonVOV2 personVOV2) {
        logger.info("Creating one person v2!");
        var entity = personMapper.convertVoTOEntity(personVOV2);
        repository.save(entity);
        var voV2 = personMapper.convertEntityToVo(entity);
        return voV2;
    }
    public PersonVO update(PersonVO personVO) {

        logger.info("Updating one person!");

        var entity = repository.findById(personVO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(personVO.getFirstName());
        entity.setLastName(personVO.getLastName());
        entity.setAddress(personVO.getAddress());
        entity.setGender(personVO.getGender());

        var vo = dozerMapper.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);
    }


}
