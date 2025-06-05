package com.example.jpademojava;

import com.example.jpademojava.entity.User;
import com.example.jpademojava.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    public void testCreateUser() {
        User user = userService.createUser("test@example.com", "Test", "User");
        
        assertNotNull(user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
        assertNotNull(user.getCreatedAt());
    }
    
    @Test
    public void testGetUserById() {
        User createdUser = userService.createUser("test2@example.com", "Test2", "User2");
        
        Optional<User> foundUser = userService.getUserById(createdUser.getId());
        
        assertTrue(foundUser.isPresent());
        assertEquals("test2@example.com", foundUser.get().getEmail());
    }
    
    @Test
    public void testGetUserByEmail() {
        userService.createUser("test3@example.com", "Test3", "User3");
        
        Optional<User> foundUser = userService.getUserByEmail("test3@example.com");
        
        assertTrue(foundUser.isPresent());
        assertEquals("Test3", foundUser.get().getFirstName());
    }
    
    @Test
    public void testUpdateUser() {
        User createdUser = userService.createUser("test4@example.com", "Test4", "User4");
        
        User updatedUser = userService.updateUser(createdUser.getId(), "updated@example.com", "Updated", "User");
        
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals("Updated", updatedUser.getFirstName());
        assertEquals("User", updatedUser.getLastName());
    }
    
    @Test
    public void testSearchByFirstName() {
        userService.createUser("search1@example.com", "SearchTest", "User1");
        userService.createUser("search2@example.com", "searchtest", "User2");
        
        List<User> users = userService.searchByFirstName("SearchTest");
        
        assertEquals(2, users.size()); // Case insensitive search
    }
}
