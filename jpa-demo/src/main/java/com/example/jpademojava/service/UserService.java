package com.example.jpademojava.service;

import com.example.jpademojava.entity.User;
import com.example.jpademojava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // Create a new user
    public User createUser(String email, String firstName, String lastName) {
        User user = new User(email, firstName, lastName);
        return userRepository.save(user);
    }
    
    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    // Get user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // Update user
    public User updateUser(Long id, String email, String firstName, String lastName) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found with id: " + id);
    }
    
    // Delete user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    
    // Search users by first name
    public List<User> searchByFirstName(String firstName) {
        return userRepository.findByFirstNameIgnoreCase(firstName);
    }
    
    // Search users by last name containing
    public List<User> searchByLastNameContaining(String lastName) {
        return userRepository.findByLastNameContaining(lastName);
    }
}
