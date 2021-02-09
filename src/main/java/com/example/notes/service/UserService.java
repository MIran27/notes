package com.example.notes.service;

import com.example.notes.common.ApiResponse;
import com.example.notes.common.AuthenticationRequestDto;
import com.example.notes.common.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;



public interface UserService extends UserDetailsService  {
    ApiResponse login(AuthenticationRequestDto authenticationRequestDto);

    UserDto createUser(UserDto userDto);

}
