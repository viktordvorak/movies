package cz.dvorakv.service;

import cz.dvorakv.dto.CustomerDto;

import java.util.List;

/**
 * @author dvora
 * @since 5.7.2024
 */
public interface CustomerService {

    CustomerDto addCustomer(CustomerDto dto);

    List<CustomerDto> getCustomers();

}
