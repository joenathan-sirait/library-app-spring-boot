package net.springBootApp.apiLibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingContoller {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "username", defaultValue = "world") String name){
        return String.format("Hello %s ", name);
    }
}
