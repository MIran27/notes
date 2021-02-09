package com.example.notes.service.impl;

import com.example.notes.common.ApiResponse;
import com.example.notes.common.AuthenticationRequestDto;
import com.example.notes.common.UserDto;
import com.example.notes.entity.NoteUser;
import com.example.notes.repository.UserRepository;
import com.example.notes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

/**
 * @author miran_jayasinghe
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        NoteUser user = userRepository.findById(s).orElse(null);
        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }

    @Override
    public ApiResponse login(AuthenticationRequestDto authenticationRequestDto){
        NoteUser user = userRepository.findByUsername(authenticationRequestDto.getUsername());

        if(user == null) {
            throw new RuntimeException("User does not exist.");
        }
        if(!user.getPassword().equals(authenticationRequestDto.getPassword())){
            throw new RuntimeException("Password mismatch.");
        }
            return new ApiResponse(200,"Success",user);
    }

    @Override
    public UserDto createUser(UserDto userDto){
        NoteUser noteUser = new NoteUser();
        noteUser.setPassword(userDto.getPassword());
        noteUser.setUsername(userDto.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = passwordEncoder(userDto.getPassword());
        System.out.println(bCryptPasswordEncoder);
        userRepository.save(noteUser);
        userDto.setPassword(null);
        return userDto;
    }



    private BCryptPasswordEncoder passwordEncoder(String password) {
        return new BCryptPasswordEncoder();
    }
}
