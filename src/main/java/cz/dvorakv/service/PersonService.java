package cz.dvorakv.service;

import cz.dvorakv.constant.RoleType;
import cz.dvorakv.dto.PersonDto;

import java.util.List;

public interface PersonService {

    PersonDto addPerson(PersonDto personDto);

    List<PersonDto> getPersons(RoleType role, int limit);

    PersonDto getPerson(Long id);

    PersonDto updatePerson(PersonDto request, Long id);

    PersonDto deletePerson(Long id);

}
