package net.springBootApp.apiLibrary.services.service;

import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.dto.request.BorrowDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;



public interface BorrowService {
    ResponseData<Object> createBorrow(long id, BorrowDto requesDto)throws CustomNotFoundExc, Exception;
    ResponseData<Object> getById(long id) throws CustomNotFoundExc, Exception;
    ResponseData<Object> getBorrow(Boolean status) throws CustomNotFoundExc;
    ResponseData<Object> deleteBorrow(Long id) throws CustomNotFoundExc, Exception ;
}
