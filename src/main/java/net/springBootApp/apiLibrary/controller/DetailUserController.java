package net.springBootApp.apiLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.springBootApp.apiLibrary.model.dto.ResponseData;
import net.springBootApp.apiLibrary.model.dto.UserDto;
import net.springBootApp.apiLibrary.service.DetailUserService;

@RestController
@RequestMapping("/detail-users")
public class DetailUserController {
    @Autowired
    private DetailUserService detailUserService;
    private ResponseData<Object> responseData;

    
    @PutMapping("{id}")
    public ResponseEntity<Object> updateDetailUser(@PathVariable long id, @RequestBody UserDto request){
        responseData = detailUserService.updateDetailUsers(id, request);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
