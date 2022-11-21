package net.springBootApp.apiLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootApp.apiLibrary.model.dto.request.UserDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.services.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;



    private ResponseData<Object> responseData;

    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody UserDto request){
        responseData = userService.register(request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> signIn(@RequestBody UserDto request){
        responseData = userService.login(request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable long id, @RequestBody UserDto request){
        responseData = userService.updateUsers(id, request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

}       