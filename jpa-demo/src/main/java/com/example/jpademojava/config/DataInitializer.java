package com.example.jpademojava.config;

import com.example.jpademojava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserService userService;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize some sample data
        userService.createUser("john.doe@example.com", "John", "Doe");
        userService.createUser("jane.smith@example.com", "Jane", "Smith");
        userService.createUser("bob.johnson@example.com", "Bob", "Johnson");
        
        System.out.println("Sample data initialized!");
        System.out.println("Total users: " + userService.getAllUsers().size());
    }
}
