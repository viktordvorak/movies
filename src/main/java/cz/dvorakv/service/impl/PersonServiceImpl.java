package cz.dvorakv.service.impl;

import cz.dvorakv.constant.RoleType;
import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.dto.mapper.PersonMapper;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.entity.repository.PersonRepository;
import cz.dvorakv.service.PersonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper mapper;

    @Override
    public PersonDto addPerson(final PersonDto personDto) {
        val entity = mapper.toEntity(personDto);
        val savedEntity = personRepository.save(entity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public List<PersonDto> getPersons(final RoleType role, final int limit) {
        val entities = personRepository.getAllByRole(role, limit);
        val persons = new ArrayList<PersonDto>();

        entities.forEach(e -> persons.add(mapper.toDto(e)));
        return persons;
    }

    @Override
    public PersonDto getPerson(Long id) {
        val entity = personRepository.getReferenceById(id);
        return mapper.toDto(entity);
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto, Long id) {
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Person id not found:" + id);
        }
        val personEntity = mapper.toEntity(personDto);
        personEntity.setId(personDto.getId());
        val savedEntity = personRepository.save(personEntity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public PersonDto deletePerson(Long id) {
        val entity = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        personRepository.deleteById(id);
        return mapper.toDto(entity);
    }
}
