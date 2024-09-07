package cz.dvorakv.service.impl;


import cz.dvorakv.dto.CustomerDto;
import cz.dvorakv.dto.mapper.CustomerMapper;
import cz.dvorakv.entity.repository.CustomerRepository;
import cz.dvorakv.service.CustomerService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dvora
 * @since 5.7.2024
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerMapper mapper;

    public CustomerDto addCustomer(CustomerDto dto) {
        val entity = mapper.toEntity(dto);
        val savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }

    public List<CustomerDto> getCustomers() {
        return repository.findAll().stream()
                 .map(c -> mapper.toDto(c))
                 .collect(Collectors.toList());
    }

}
