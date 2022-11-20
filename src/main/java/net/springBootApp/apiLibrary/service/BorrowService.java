package net.springBootApp.apiLibrary.service;

import net.springBootApp.apiLibrary.model.dto.BorrowDto;
import net.springBootApp.apiLibrary.model.dto.ResponseData;

public interface BorrowService {
    ResponseData<Object> createBorrow(BorrowDto requesDto);
    ResponseData<Object> getAll();
    ResponseData<Object> getById(long id);
    ResponseData<Object> getBorrow(Boolean status);
    ResponseData<Object> deleteBorrow(Long id);
}
