package cz.dvorakv.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@Profile("!test")
public class TestController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }
}
