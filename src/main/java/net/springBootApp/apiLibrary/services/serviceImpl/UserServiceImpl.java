package net.springBootApp.apiLibrary.services.serviceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.springBootApp.apiLibrary.model.dto.request.UserDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.model.entity.DetailUser;
import net.springBootApp.apiLibrary.model.entity.User;
import net.springBootApp.apiLibrary.repository.DetailUserRepository;
import net.springBootApp.apiLibrary.repository.UserRepository;
import net.springBootApp.apiLibrary.services.service.UserService;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private DetailUserRepository detailUserRepository;

    private ResponseData<Object> responseData;
    private User user;
    private DetailUser detailUser;
    private Map<Object, Object> data;

    @Override
    public ResponseData<Object> register(UserDto request) {
        // TODO Auto-generated method stub
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "Email already registered , please login", null);
        } else {
            user = new User(request.getEmail(), request.getPassword());
            userRepository.save(user);

            detailUser = new DetailUser(request.getFirstName(), request.getLastName(), request.getPhoneNumber());
            detailUser.setUserId(user);
            detailUser.setUserEmail(user);
            detailUserRepository.save(detailUser);
            data = new HashMap<>();
            data.put("email", user.getEmail());
            data.put("firstName", detailUser.getFirstName());
            data.put("lastName", detailUser.getLastName());
            data.put("phoneNumber", detailUser.getPhoneNumber());
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Succes Registered", data);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> login(UserDto request) {
        // TODO Auto-generated method stub
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            user = userOpt.get();

            if (request.getPassword().equals(user.getPassword())) {
                data = new HashMap<>();
                data.put("email", user.getEmail());

                responseData = new ResponseData<Object>(HttpStatus.OK.value(), "SUCCES LOGIN !!", data );
            } else {
                responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "wrong emaiL or password", null);
            }
        } else {
            responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User Not Found , Please register account", null);
            
        }
        return responseData;
    }    
    
    public ResponseData<Object> updateUsers(Long id , UserDto request){
        Optional<User> userOpt = userRepository.findById(id); 
        
        if(userOpt.isPresent()){
            user = userOpt.get();
            Optional<DetailUser> detailUserOpt = detailUserRepository.findByUserId(user);
            if(detailUserOpt.isPresent()){
            detailUser = detailUserOpt.get();
            detailUser.setFirstName(request.getFirstName());
            detailUser.setLastName(request.getLastName());
            detailUser.setPhoneNumber(request.getLastName());
            detailUserRepository.save(detailUser);
            userRepository.save(user);
            } else {
                detailUser = new DetailUser(request.getFirstName(), request.getLastName(), request.getPhoneNumber());
                detailUser.setUserId(user);
                detailUser.setUserEmail(user);
                detailUserRepository.save(detailUser);
            }
            
            data = new HashMap<>();
            data.put("Email", user.getEmail());
            // data.put("firstName", request.getFirstName());
            data.put("firstName", detailUser.getFirstName());
            data.put("lastName", detailUser.getLastName());
            data.put("phoneNUmber", detailUser.getPhoneNumber());
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Succes Update Account", data);
        } 
        else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
        }
        return responseData;
    }
}
