package com.payten.hacka.auth_service.controller;

import com.payten.hacka.auth_service.dto.AddressDto;
import com.payten.hacka.auth_service.dto.CreateUserDto;
import com.payten.hacka.auth_service.dto.UserDetailDto;
import com.payten.hacka.auth_service.dto.UserDto;
import com.payten.hacka.auth_service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody CreateUserDto userDto){
        return new ResponseEntity<>(userService.registerUser(userDto), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> finsUser(@PathVariable("id") String email){
        return new ResponseEntity<>(userService.getUserInfo(email), HttpStatus.OK);
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<UserDetailDto> finsUser(@PathVariable("id") UUID id, @RequestBody AddressDto addressDto){
        return new ResponseEntity<>(userService.updateAddress(id, addressDto), HttpStatus.OK);
    }

}
