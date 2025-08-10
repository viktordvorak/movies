package cz.dvorakv.unit.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.dvorakv.constant.RoleType;
import cz.dvorakv.controller.PersonController;
import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.service.PersonService;
import lombok.val;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    private final ObjectMapper objectMapper = new ObjectMapper();

//    @Test
//    void testAddPerson() throws Exception {
//        val person = new PersonDto();
//        person.setName("John Doe");
//        person.setCountry("USA");
//
//        Mockito.when(personService.addPerson(Mockito.any(PersonDto.class)))
//                .thenReturn(person);
//
//        mockMvc.perform(post("/api/persons")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(person)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.country").value("USA"));
//    }
//
//    @Test
//    void testGetAllPersons() throws Exception {
//        List<PersonDto> people = List.of(
//                new PersonDto(1L, "John", Date.valueOf(LocalDate.MIN), "Czech", "...", RoleType.ACTOR),
//                new PersonDto(2L, "Anna", Date.valueOf(LocalDate.MIN), "UK", "...", RoleType.ACTOR)
//        );
//
//        Mockito.when(personService.getPersons(RoleType.ACTOR, 2)).thenReturn(people);
//
//        mockMvc.perform(get("/api/persons"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2))
//                .andExpect(jsonPath("$[0].name").value("John"))
//                .andExpect(jsonPath("$[1].country").value("UK"));
//    }
}

