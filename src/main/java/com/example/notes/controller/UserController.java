package com.example.notes.controller;

import com.example.notes.common.ApiResponse;
import com.example.notes.common.AuthenticationRequestDto;
import com.example.notes.common.UserDto;
import com.example.notes.service.UserService;
import com.example.notes.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author miran_jayasinghe
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/user/login")
    public ApiResponse createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto)throws Exception {
        try{
            userService.login(authenticationRequestDto);
            String jwt = jwtUtil.generateToken(authenticationRequestDto);
            return new ApiResponse(200,"SUCCESS",jwt);
        }catch(Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }
    }

    @PostMapping("/user")
    public ApiResponse createUser(@RequestBody UserDto userDto)throws Exception {
        try{
            UserDto user = userService.createUser(userDto);
            AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
            authenticationRequestDto.setPassword(userDto.getPassword());
            authenticationRequestDto.setUserName(userDto.getUsername());
            final String jwt = jwtUtil.generateToken(authenticationRequestDto);
            user.setToken(jwt);
            return new ApiResponse(201,"USER CREATED SUCCESSFULLY", user);
        }catch(Exception e){
            return new ApiResponse(500,"ERROR",e.getMessage());
        }

    }
}
