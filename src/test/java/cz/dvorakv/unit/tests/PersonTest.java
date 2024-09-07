package cz.dvorakv.unit.tests;

import cz.dvorakv.constant.RoleType;
import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.dto.mapper.PersonMapper;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.entity.repository.PersonRepository;
import cz.dvorakv.service.PersonService;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper mapper;

    @Test
    public void testAddPerson() {
        val person = new PersonEntity();
        person.setName("John Doe");
        person.setBirthDate(new Date());
        person.setCountry("USA");
        person.setBiography("Biography");
        person.setRole(RoleType.DIRECTOR);

        Mockito.when(personRepository.save(any(PersonEntity.class))).thenReturn(person);

        val created = personService.addPerson(mapper.toDto(person));

        Assert.assertEquals("John Doe", created.getName());
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }

    @Test
    public void testUpdatePerson() {
        val person = new PersonEntity();
        person.setName("Jane Doe");
        person.setBirthDate(new Date());
        person.setCountry("USA");
        person.setBiography("Updated Biography");
        person.setRole(RoleType.ACTOR);

        //Mockito.when(personRepository.save(any(PersonEntity.class))).thenReturn(person);

        PersonDto updated = personService.updatePerson(mapper.toDto(person), 1L);

        Assert.assertEquals("Jane Doe", updated.getName());
        Mockito.verify(personRepository, Mockito.times(1)).save(person);
    }

    @Test
    public void testGetPerson() {
        PersonEntity person = new PersonEntity();
        person.setId(1L);
        person.setName("John Doe");

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        val personFound = personService.getPerson(1L);

        Assert.assertEquals(personFound, person);
        Mockito.verify(personRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    public void testDeletePerson() {
        val id = 1L;
        personService.deletePerson(id);
        Mockito.verify(personRepository, Mockito.times(1)).deleteById(id);
    }

}
