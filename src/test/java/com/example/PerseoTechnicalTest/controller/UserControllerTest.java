package com.example.PerseoTechnicalTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.PerseoTechnicalTest.model.ERole;
import com.example.PerseoTechnicalTest.model.User;
import com.example.PerseoTechnicalTest.service.UserService;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private User userJohn;
    private User userJane;
    private ArrayList<User> usersList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        userJohn = new User();
        userJohn.setId(1L);
        userJohn.setUsername("john_doe");
        userJohn.setPassword("password");
        userJohn.setEmail("john.doe@example.com");
        userJohn.setRole(ERole.ADMIN);

        userJane = new User();
        userJane.setId(2L);
        userJane.setUsername("jane_doe");
        userJane.setPassword("password");
        userJane.setEmail("jane.doe@example.com");
        userJane.setRole(ERole.USER);

        usersList.add(userJohn);
        usersList.add(userJane);
    }

    @Test
    void createUser() throws Exception {
        when(userService.createUser(any(User.class))).thenReturn(userJohn);

        String userJson =
                "{\"id\": 1,\n"
                        + "\"username\": \"john_doe\",\n"
                        + "\"password\": \"password\",\n"
                        + "\"email\": \"john.doe@example.com\",\n"
                        + "\"role\": \"ADMIN\"\n"
                        + "}";

        mockMvc
                .perform(post("/api/user/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().json(userJson));
    }

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(usersList);

        mockMvc
                .perform(get("/api/user/get"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\n"
                        + "  {\n"
                        + "    \"id\": 1,\n"
                        + "    \"username\": \"john_doe\",\n"
                        + "    \"password\": \"password\",\n"
                        + "    \"email\": \"john.doe@example.com\",\n"
                        + "    \"role\": \"ADMIN\"\n"
                        + "  },\n"
                        + "  {\n"
                        + "    \"id\": 2,\n"
                        + "    \"username\": \"jane_doe\",\n"
                        + "    \"password\": \"password\",\n"
                        + "    \"email\": \"jane.doe@example.com\",\n"
                        + "    \"role\": \"USER\"\n"
                        + "  }\n"
                        + "]"));
    }

    @Test
    void getUserById() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(Optional.of(userJohn));

        mockMvc
                .perform(get("/api/user/get/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n"
                        + "  \"id\": 1,\n"
                        + "  \"username\": \"john_doe\",\n"
                        + "  \"password\": \"password\",\n"
                        + "  \"email\": \"john.doe@example.com\",\n"
                        + "  \"role\": \"ADMIN\"\n"
                        + "}"));
    }

    @Test
    void updateUser() throws Exception {
        User updatedUser = new User();
        updatedUser.setId(2L);
        updatedUser.setEmail("updated.email@example.com");
        updatedUser.setRole(ERole.USER);

        String updateUserJson =
                "{\"id\": 2,\n"
                        + "\"email\": \"updated.email@example.com\",\n"
                        + "\"role\": \"USER\"\n"
                        + "}";

        when(userService.updateUser(any(User.class), anyLong())).thenReturn(updatedUser);

        mockMvc
                .perform(put("/api/user/put/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserJson))
                .andExpect(status().isOk())
                .andExpect(content().json(updateUserJson));

        verify(userService).updateUser(any(User.class), anyLong());
    }

    @Test
    void deleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc
                .perform(delete("/api/user/delete/1"))
                .andExpect(status().isOk());

        verify(userService).deleteUser(1L);
    }
}
