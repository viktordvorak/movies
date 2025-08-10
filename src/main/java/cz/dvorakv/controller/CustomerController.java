package cz.dvorakv.controller;

import cz.dvorakv.dto.CustomerDto;
import cz.dvorakv.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping({"/customer", "customer/"})
    public CustomerDto addCustomer(final @RequestBody CustomerDto dto) {
        return service.addCustomer(dto);
    }

    @GetMapping({"/customers", "customers/"})
    public List<CustomerDto> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping({"/customers/{id}", "customers/{id}"})
    public CustomerDto getCustomer(final Long id) {
        return service.getCustomer(id);
    }

}
