package controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class HomeController {
    @GetMapping("/")
    public String greetings(){
        return "Welcome to the inventory management service";
    }


}