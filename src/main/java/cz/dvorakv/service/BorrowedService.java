package cz.dvorakv.service;

import cz.dvorakv.dto.BorrowedDto;
import cz.dvorakv.entity.CustomerEntity;
import cz.dvorakv.entity.MovieEntity;
import cz.dvorakv.entity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author dvora
 * @since 13.7.2024
 */
public interface BorrowedService {

    BorrowedDto createBorrowed(BorrowedDto dto);

    CustomerEntity mapCustomer(Long id);

    MovieEntity mapMovie(Long movieID);

}
