package cz.dvorakv.controller;

import cz.dvorakv.dto.CustomerDto;
import cz.dvorakv.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping({"/customer", "customer/"})
    public CustomerDto addCustomer(final @RequestBody CustomerDto dto) {
        return service.addCustomer(dto);
    }

}
