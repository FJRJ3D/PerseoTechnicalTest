package com.example.PerseoTechnicalTest.service;

import com.example.PerseoTechnicalTest.model.User;
import com.example.PerseoTechnicalTest.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock private IUserRepository iUserRepository;
    @InjectMocks private UserService userService;

    private User user1;
    private User user2;

    private List<User> userList = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        user1 = new User();
        user1.setId(1L);
        user1.setUsername("john_doe");
        user1.setEmail("john@example.com");

        user2 = new User();
        user2.setId(2L);
        user2.setUsername("jane_doe");
        user2.setEmail("jane@example.com");

        userList.add(user1);
        userList.add(user2);
    }

    @Test
    void test_create_user() {
        when(iUserRepository.save(any(User.class))).thenReturn(user1);

        User result = userService.createUser(user1);

        assertEquals(1L, result.getId());
        assertEquals("john_doe", result.getUsername());
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void test_get_all_users() {
        when(iUserRepository.findAll()).thenReturn(userList);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("john_doe", result.get(0).getUsername());
        assertEquals("jane_doe", result.get(1).getUsername());
    }

    @Test
    void test_get_user_by_id() {
        when(iUserRepository.findById(any(Long.class))).thenReturn(Optional.of(user1));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("john_doe", result.get().getUsername());
    }

    @Test
    void test_update_user() {
        when(iUserRepository.save(any(User.class))).thenReturn(user1);

        user1.setUsername("johnny_doe");
        User result = userService.updateUser(user1, 1L);

        assertEquals(1L, result.getId());
        assertEquals("johnny_doe", result.getUsername());
    }

    @Test
    void test_delete_user() {
        doNothing().when(iUserRepository).deleteById(any(Long.class));

        userService.deleteUser(1L);

        verify(iUserRepository, times(1)).deleteById(1L);
    }
}
