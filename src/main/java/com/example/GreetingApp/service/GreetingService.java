package com.example.GreetingApp.service;

import com.example.GreetingApp.model.Greeting;
import com.example.GreetingApp.model.User;
import com.example.GreetingApp.repository.GreetingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService implements IGreetingService {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private GreetingRepository greetingRepository;

    @Override
    public Greeting addGreeting(User user) {
        String message = String.format(template, (user.toString().isEmpty()) ? "Hello World" : user.toString());

        Greeting greeting = new Greeting(); // Create a new object
        greeting.setMessage(message); // Set only the message, no manual ID assignment

        return greetingRepository.save(greeting); // Save in DB
    }


    @Override
    public Greeting getGreetingById(long id) {
        return greetingRepository.findById(id).orElse(null);
    }

    public List<Greeting> getAllGreetings() {
        return greetingRepository.findAll();
    }
    @Transactional
    public Greeting updateGreeting(Long id, String message) {
        return greetingRepository.findById(id).map(greeting -> {
            greeting.setMessage(message);
            return greetingRepository.saveAndFlush(greeting); // Ensures immediate update
        }).orElse(null);
    }


    public void deleteGreeting(Long id) {
        greetingRepository.deleteById(id);
    }
}