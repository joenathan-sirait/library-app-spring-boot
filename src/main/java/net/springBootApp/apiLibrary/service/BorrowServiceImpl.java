package net.springBootApp.apiLibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.springBootApp.apiLibrary.model.dto.BorrowDto;
import net.springBootApp.apiLibrary.model.dto.ResponseData;
import net.springBootApp.apiLibrary.model.entity.Book;
import net.springBootApp.apiLibrary.model.entity.Borrow;
import net.springBootApp.apiLibrary.model.entity.User;
import net.springBootApp.apiLibrary.repository.BookRepository;
import net.springBootApp.apiLibrary.repository.BorrowRepository;
import net.springBootApp.apiLibrary.repository.UserRepositroy;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepositroy userRepositroy;

    @Autowired
    private BorrowRepository borrowRepository;

    private ResponseData<Object> responseData;
    private Book book;
    private List<Borrow> borrows;
    private Borrow borrow;
    private User user;


    @Override
    public ResponseData<Object> createBorrow(BorrowDto requesDto) {
        // TODO Auto-generated method stub
        borrow = new Borrow();
        book = bookRepository.findByTitle(requesDto.getBookName());
        borrow.setBook(book);
        // user = userRepositroy.findById(requesDto.getId());
        borrow.setUser(user);
        borrowRepository.save(borrow);
        responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "add successfully", borrow);

        return responseData;
    }

    @Override
    public ResponseData<Object> getById(long id) {
     Optional<Borrow> borrowOpt = borrowRepository.findById(id);
    if (borrowOpt.isPresent()) {
      borrow = borrowOpt.get();
      responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", borrow);
    } else {
      responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
    }
    return responseData;
    }

    @Override
    public ResponseData<Object> getBorrow(Boolean status) {
        if (status == null) {
            borrows = borrowRepository.findAll();
          } else {
            borrows = borrowRepository.findByBorrowed(status);
          }
      
          // bisa dikasih validasi kalau list category masih kosong, belum ada data
          if (borrows.isEmpty()) {
          responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "Data Is Empty", null);
              
          } else {
          responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", borrows);
              
          }
          // category
          return responseData;
    }

    @Override
    public ResponseData<Object> deleteBorrow(Long id) {
     Optional<Borrow> borrowOpt = borrowRepository.findById(id);
    // bisa dikasih validasi kalau buku ada atau tidak
    if (borrowOpt.isPresent()) {
        borrow = borrowOpt.get();
        // update namenya
        borrow.setBorrowed(false);
        // save
        borrowRepository.save(borrow);

        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Thanks for borrow", borrow);
    } else {
        responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "data not found", null);
        

    }
    return responseData;
    }
    
}
