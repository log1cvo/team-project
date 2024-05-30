package com.example.issuemanager;

import com.example.issuemanager.model.User;
import com.example.issuemanager.repository.UserRepository;
import com.example.issuemanager.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertThat(createdUser.getUsername()).isEqualTo("testuser");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testFindUserByUsername() {
        User user = new User();
        user.setUsername("testuser");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        User foundUser = userService.findUserByUsername("testuser");

        assertThat(foundUser.getUsername()).isEqualTo("testuser");
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    public void testFindUserById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User foundUser = userService.findUserById(1L);

        assertThat(foundUser.getId()).isEqualTo(1L);
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("existingUser");
        existingUser.setPassword("password");
        existingUser.setRole("USER");

        User updatedDetails = new User();
        updatedDetails.setUsername("updatedUser");
        updatedDetails.setPassword("newpassword");
        updatedDetails.setRole("ADMIN");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedDetails);

        User updatedUser = userService.updateUser(1L, updatedDetails);

        assertThat(updatedUser.getUsername()).isEqualTo("updatedUser");
        assertThat(updatedUser.getPassword()).isEqualTo("newpassword");
        assertThat(updatedUser.getRole()).isEqualTo("ADMIN");
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
    }
}
