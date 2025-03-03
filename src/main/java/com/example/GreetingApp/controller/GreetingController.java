package com.example.GreetingApp.controller;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.model.User;
import com.example.GreetingApp.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/greetings")
public class GreetingController {
    @Autowired
    private GreetingService service;

   // @PostMapping
   // public Greeting createGreeting(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
    //    User user = new User(firstName, lastName);
   //    return service.addGreeting(user);
   // }
    @PostMapping
    public Greeting createGreeting(@RequestBody User user) {
        return service.addGreeting(user);
    }
    @PostMapping("/{id}")
    public String  createGreeting(@PathVariable Long id) {return "heello";}


    @GetMapping("/{id}")
    public Greeting getGreetingById(@PathVariable Long id) {
        return service.getGreetingById(id);
    }

    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return service.getAllGreetings();
    }

    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestParam String message) {
        return service.updateGreeting(id, message);
    }

    @DeleteMapping("/{id}")
    public void deleteGreeting(@PathVariable Long id) {
        service.deleteGreeting(id);
    }
}