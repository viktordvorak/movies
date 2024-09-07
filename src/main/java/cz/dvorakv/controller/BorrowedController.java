package cz.dvorakv.controller;

import cz.dvorakv.dto.BorrowedDto;
import cz.dvorakv.service.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BorrowedController {

    @Autowired
    private BorrowedService service;

    @PostMapping({"/borrowed", "borrowed/"})
    public BorrowedDto createBorrowed(final @RequestBody BorrowedDto dto) {
        return service.createBorrowed(dto);
    }

}
