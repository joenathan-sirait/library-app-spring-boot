package net.springBootApp.apiLibrary.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.springBootApp.apiLibrary.model.dto.ResponseData;
import net.springBootApp.apiLibrary.model.dto.UserDto;
import net.springBootApp.apiLibrary.model.entity.DetailUser;
import net.springBootApp.apiLibrary.repository.DetailUserRepository;

@Service
@Transactional
public class DetailUserServiceImpl implements DetailUserService {


    @Autowired 
    private DetailUserRepository detailUserRepository;

    private ResponseData<Object> responseData;

    private DetailUser detailUser;
    private Map<Object, Object> data;
    @Override
    public ResponseData<Object> updateDetailUsers(Long id, UserDto request) {
         Optional<DetailUser> detailUserOpt = detailUserRepository.findById(id);
        if(detailUserOpt.isPresent()){
            detailUser = detailUserOpt.get();
            detailUser.setFirstName(request.getFirstName());
            detailUser.setLastName(request.getLastName());
            detailUser.setPhoneNumber(request.getPhoneNumber());
            detailUserRepository.save(detailUser);
            data = new HashMap<>();
            data.put("firstName", detailUser.getFirstName());
            data.put("lastName", detailUser.getLastName());
            data.put("phoneNumber", detailUser.getPhoneNumber());
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Succes Update Account", data);    
        }
        
        else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
        }
        return responseData;
    }
    
}
