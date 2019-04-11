package hu.bme.sch.bss.webcentral.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = "application/json")
public class HomeController {

    private static final String WELCOME = "Hello";

    @RequestMapping("/")
    public final String home() {
        return WELCOME;
    }
}
