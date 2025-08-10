package cz.dvorakv.controller;

import cz.dvorakv.constant.BorrowStatus;
import cz.dvorakv.dto.BorrowedDto;
import cz.dvorakv.service.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BorrowedController {

    @Autowired
    private BorrowedService service;

    @PostMapping({"/borrowed", "borrowed/"})
    public BorrowedDto createBorrowed(final @RequestBody BorrowedDto dto) {
        return service.createBorrowed(dto);
    }

    @GetMapping({"/borrowed/{id}", "borrowed/{id}"})
    public BorrowedDto getBorrowed(final @PathVariable Long id) {
        return service.getBorrowed(id);
    }

    @GetMapping({"/borrowed", "borrowed/"})
    public List<BorrowedDto> getBorrowedAll() {
        return service.getBorrowedAll();
    }

    @GetMapping({"/statuses", "/statuses/"})
    public List<BorrowStatus> getStatuses() {
        return Arrays.stream(BorrowStatus.values()).collect(Collectors.toList());
    }

}
