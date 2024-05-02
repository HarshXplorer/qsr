package com.qsr;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.qsr.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.qsr.dao.UserRepository;
import com.qsr.entity.User;
import com.qsr.exception.ThrowValidException;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testFindAllValues() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> result = userService.findAllValues();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindAllValues_NoUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        assertThrows(ThrowValidException.class, () -> {
            userService.findAllValues();
        });
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testFindOne() {
        User user = new User();
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        User result = userService.findOne(id);

        assertEquals(user, result);
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void testFindOne_NotFound() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ThrowValidException.class, () -> {
            userService.findOne(id);
        });
        verify(userRepository, times(1)).findById(id);
    }
}

