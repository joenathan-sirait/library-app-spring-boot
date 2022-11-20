package net.springBootApp.apiLibrary.service;

import net.springBootApp.apiLibrary.model.dto.ResponseData;
import net.springBootApp.apiLibrary.model.dto.UserDto;

public interface DetailUserService {
    ResponseData<Object> updateDetailUsers(Long id , UserDto request);
}
