package com.josHoangDev.userservice;

import com.josHoangDev.userservice.data.User;
import com.josHoangDev.userservice.data.UserRepository;
import com.josHoangDev.userservice.model.UserDTO;
import com.josHoangDev.userservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;
    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        user = new User(1L,"dev@gmail.com","123456","employeeID");
        userDTO = new UserDTO(1L,"dev@gmail.com","123456","employeeID","token","refresh");
        ReflectionTestUtils.setField(userService, "userRepository", userRepository);
        ReflectionTestUtils.setField(userService, "passwordEncoder", passwordEncoder);
    }

    @Test
    void getAllUser() {
        List<User> users = new ArrayList<User>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);
        Assertions.assertEquals(users,userService.getAllUser());
    }

    @Test
    void saveUser() {
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userRepository.save(any(User.class))).thenReturn(user);
        Assertions.assertEquals(user.getId(), userService.saveUser(user).getId());
    }

    @Test
    void loginWithUserNotNullAndMatchesPassword(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(true);
        Assertions.assertNotNull(userService.login(user.getUsername(),user.getPassword()));
    }
    @Test
    void loginWithUserNotNullAndNotMatchesPassword(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(passwordEncoder.matches(anyString(),anyString())).thenReturn(false);
        Assertions.assertNotNull(userService.login(user.getUsername(),user.getPassword()));
    }
    @Test
    void loginWithUserNull(){
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        Assertions.assertNotNull(userService.login(user.getUsername(),user.getPassword()));
    }
}
