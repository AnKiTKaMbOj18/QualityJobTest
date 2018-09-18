package com.QualityJobTest.service;

import com.QualityJobTest.domain.CustomUserDetail;
import com.QualityJobTest.domain.User;
import com.QualityJobTest.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Optional<User> optionalUser=userRepository.findByEmail(username);
    optionalUser.orElseThrow(()->new UsernameNotFoundException("username not found"));
    return optionalUser.map(user -> CustomUserDetail.builder().user(user).build()).get();
  }
}
