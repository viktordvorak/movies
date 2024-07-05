package cz.dvorakv.controller;

import cz.dvorakv.constant.RoleType;
import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @athor dvora
 * @since 21.10.2023
 */

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Secured("ROLE_ADMIN")
    @PostMapping({"/person", "person/"})
    public PersonDto addPerson(@RequestBody final PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping({"/actors", "/actors/"})
    public List<PersonDto> getActors(@RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit) {
        return personService.getPersons(RoleType.ACTOR, limit);
    }

    @GetMapping({"/directors", "/directors/"})
    public List<PersonDto> getDirectors(@RequestParam(required = false, defaultValue = Integer.MAX_VALUE + "") int limit) {
        return personService.getPersons(RoleType.DIRECTOR, limit);
    }

    @GetMapping("/person/{id}")
    public PersonDto getPerson(@PathVariable Long id) {
        return personService.getPerson(id);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/person/{id}")
    public PersonDto updatePerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto, id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/person/{id}")
    public PersonDto deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }

    public String getCookieValue(@CookieValue String userName) {
        return userName;
    }

}
