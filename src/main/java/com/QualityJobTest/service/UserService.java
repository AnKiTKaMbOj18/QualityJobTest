package com.QualityJobTest.service;


import com.QualityJobTest.domain.User;
import com.QualityJobTest.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {


  List<User> getAllUsers();

  Optional<User> getUser(Long id);

  Long addUser(UserDto userDto);

  Boolean updateUser(UserDto userDto);

  Boolean deleteUser(Long id);
}
