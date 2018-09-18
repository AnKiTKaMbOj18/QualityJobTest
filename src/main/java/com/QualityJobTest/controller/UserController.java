package com.QualityJobTest.controller;

import com.QualityJobTest.domain.User;
import com.QualityJobTest.dto.UserDto;
import com.QualityJobTest.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  private static final String OK = "OK";


  @GetMapping("/login")
  public List<UserDto> login() {
    return userService.getAllUsers().stream().map(this::buildUserDto).collect(Collectors.toList());
  }

  @GetMapping("/")
  public List<UserDto> getAllUsers() {
    return userService.getAllUsers().stream().map(this::buildUserDto).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public UserDto getUser(@PathVariable Long id) {
    Optional<User> user= userService.getUser(id);
    return buildUserDto(user.get());
  }

  @PostMapping
  public Long addUser(@RequestBody UserDto userDto) {
    return userService.addUser(userDto);
  }

  @PutMapping
  public String updateUser(@RequestBody UserDto userDto) {
    userService.updateUser(userDto);
    return OK;
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return OK;
  }

  public UserDto buildUserDto(User user){
    return UserDto.builder()
        .id(user.getId())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }

}
