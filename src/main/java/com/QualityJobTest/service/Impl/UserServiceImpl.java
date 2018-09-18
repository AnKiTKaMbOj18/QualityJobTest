package com.QualityJobTest.service.Impl;

import com.QualityJobTest.domain.User;
import com.QualityJobTest.dto.UserDto;
import com.QualityJobTest.repository.UserRepository;
import com.QualityJobTest.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public List<User> getAllUsers() {
    List<User> users=new ArrayList<>();
    userRepository.findAll()
        .forEach(users::add);
    return users;
  }

  @Override
  public Optional<User> getUser(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public Long addUser(UserDto userDto) {

    User user=User.builder()
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .email(userDto.getEmail())
        .password(passwordEncoder.encode(userDto.getPassword()))
        .build();

    userRepository.save(user);
    return user.getId();
  }

  @Override
  public Boolean updateUser(UserDto userDto) {

    User user=userRepository.findById(userDto.getId()).get();
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

    userRepository.save(user);
    return true;
  }

  @Override
  public Boolean deleteUser(Long id) {
    userRepository.deleteById(id);
    return true;
  }
}
