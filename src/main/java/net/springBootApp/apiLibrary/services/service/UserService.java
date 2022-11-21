package net.springBootApp.apiLibrary.services.service;

import net.springBootApp.apiLibrary.model.dto.request.UserDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;

public interface UserService {
    ResponseData<Object> register(UserDto request);
    ResponseData<Object> login(UserDto request);
    ResponseData<Object> updateUsers(Long id , UserDto request);


}
