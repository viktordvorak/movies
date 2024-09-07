package cz.dvorakv.service.impl;

import cz.dvorakv.dto.BorrowedDto;
import cz.dvorakv.dto.mapper.BorrowedMapper;
import cz.dvorakv.entity.BorrowedEntity;
import cz.dvorakv.entity.CustomerEntity;
import cz.dvorakv.entity.MovieEntity;
import cz.dvorakv.entity.repository.BorrowedRepository;
import cz.dvorakv.entity.repository.CustomerRepository;
import cz.dvorakv.entity.repository.MovieRepository;
import cz.dvorakv.entity.repository.filter.MovieFilter;
import cz.dvorakv.service.BorrowedService;
import cz.dvorakv.service.MovieService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dvora
 * @since 13.7.2024
 */
@Service
public class BorrowedServiceImpl implements BorrowedService {

    @Autowired
    private BorrowedRepository borrowedRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BorrowedMapper mapper;

    public BorrowedDto createBorrowed(final BorrowedDto dto) {
        val borrowedEntity = mapper.toEntity(dto, this);
        val savedEntity = borrowedRepository.save(borrowedEntity);
        return mapper.toDto(savedEntity);
    }

    @Override
    public CustomerEntity mapCustomer(Long id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public MovieEntity mapMovie(Long movieID) {
        return movieRepository.getReferenceById(movieID);
    }

}
