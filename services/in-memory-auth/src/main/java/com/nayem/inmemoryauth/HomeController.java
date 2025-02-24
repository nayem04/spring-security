package com.nayem.inmemoryauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class HomeController {
    @GetMapping("home")
    public String home() {
        return "Welcome Home!";
    }

    @GetMapping("/user")
    public String user() {
        return "Hello User!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin";
    }
}
