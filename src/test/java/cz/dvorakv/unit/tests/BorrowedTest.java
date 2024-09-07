package cz.dvorakv.unit.tests;

import cz.dvorakv.constant.RoleType;
import cz.dvorakv.dto.BorrowedDto;
import cz.dvorakv.dto.CustomerDto;
import cz.dvorakv.dto.MovieDto;
import cz.dvorakv.dto.PersonDto;
import cz.dvorakv.dto.mapper.PersonMapper;
import cz.dvorakv.entity.BorrowedEntity;
import cz.dvorakv.entity.PersonEntity;
import cz.dvorakv.entity.repository.BorrowedRepository;
import cz.dvorakv.entity.repository.PersonRepository;
import cz.dvorakv.entity.repository.filter.MovieFilter;
import cz.dvorakv.service.BorrowedService;
import cz.dvorakv.service.CustomerService;
import cz.dvorakv.service.MovieService;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowedTest {

    @MockBean
    private BorrowedRepository borrowedRepository;

    @Autowired
    private BorrowedService borrowedService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private PersonMapper mapper;

    @Test
    public void testAddBorrowed() {
        val customers = customerService.getCustomers().stream()
                .map(CustomerDto::getId)
                .collect(Collectors.toList());
        val movies = movieService.getAllMovies(new MovieFilter()
                .setAvailability(MovieFilter.Availability.AVAILABLE)).stream()
                .map(MovieDto::getId)
                .collect(Collectors.toList());

        val borrowed = new BorrowedDto();
        borrowed.setBorrowedDate(LocalDate.now());
        borrowed.setCustomerID(customers.get(new Random().nextInt(customers.size())));
        borrowed.setMovieID(movies.get(new Random().nextInt(movies.size())));
        borrowed.setReturnedDate(LocalDate.now().minusDays(7));

        //Mockito.when(borrowedRepository.save(any(BorrowedDto.class))).thenReturn(borrowed);

        val created = borrowedService.createBorrowed(borrowed);

        //Assert.assertEquals("John Doe", created.get);
        //Mockito.verify(borrowed, Mockito.times(1)).save(borrowed);
    }

}
