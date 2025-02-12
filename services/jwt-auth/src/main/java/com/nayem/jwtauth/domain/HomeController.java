package com.nayem.jwtauth.domain;

import com.nayem.jwtauth.common.routing.Router;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping(Router.HELLO_ADMIN)
    public String helloAdmin() {
        return "Hello Admin";
    }

    @GetMapping(Router.HELLO_USER)
    public String helloUser() {
        return "Hello User!";
    }

    @GetMapping(Router.HOME)
    public String home() {
        return "Welcome Home!";
    }
}