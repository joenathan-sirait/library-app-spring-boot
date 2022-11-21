package net.springBootApp.apiLibrary.validator;

import java.util.Optional;

import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.entity.User;

public class UserValidator {
    public void validateUserNotFound( Optional<User> userOpt) throws Exception{
        if (userOpt.isEmpty()) {
            throw new CustomNotFoundExc("User not found");
          } 
    }
}
