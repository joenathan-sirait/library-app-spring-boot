package net.springBootApp.apiLibrary.service;

import net.springBootApp.apiLibrary.model.dto.ResponseData;
import net.springBootApp.apiLibrary.model.dto.UserDto;

public interface UserService {
    ResponseData<Object> register(UserDto request);
    ResponseData<Object> login(UserDto request);
    ResponseData<Object> updateUsers(Long id , UserDto request);


}
