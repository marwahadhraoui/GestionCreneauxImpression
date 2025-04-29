package com.plateformeDev.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.plateformeDev.entities.User;
import com.plateformeDev.repos.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1);
        user.setNom("Test");
        user.setEmail("test@example.com");
        user.setMdp("123456");
        user.setRole("SECRETAIRE");
    }

    @Test
    void testSaveUser() {
        when(userRepo.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("Test", savedUser.getNom());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void testGetUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        User result = userService.getUser(1);

        assertNotNull(result);
        assertEquals("Test", result.getNom());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(user);
        verify(userRepo, times(1)).delete(user);
    }

    @Test
    void testDeleteUserById() {
        userService.deleteUserById(1);
        verify(userRepo, times(1)).deleteById(1);
    }

    @Test
    void testGetAllUsers() {
        when(userRepo.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.getAllUsers();

        assertEquals(1, users.size());
    }

    @Test
    void testGetSecretaires() {
        when(userRepo.findByRole("SECRETAIRE")).thenReturn(Arrays.asList(user));

        List<User> secretaires = userService.getSecretaires();

        assertEquals(1, secretaires.size());
        assertEquals("SECRETAIRE", secretaires.get(0).getRole());
    }

    @Test
    void testFindByEmailAndPassword() {
        when(userRepo.findByEmailAndMdp("test@example.com", "123456")).thenReturn(user);

        User result = userService.findByEmailAndPassword("test@example.com", "123456");

        assertNotNull(result);
        assertEquals("Test", result.getNom());
    }
}
