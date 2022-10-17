package mate.academy.springboot.datajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class injectController {
    @GetMapping("/inject")
    private String inject() {
        return "Hello";
    }
}
