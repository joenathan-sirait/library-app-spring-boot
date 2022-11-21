package net.springBootApp.apiLibrary.services.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.dto.request.BorrowDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.model.entity.Book;
import net.springBootApp.apiLibrary.model.entity.Borrow;
import net.springBootApp.apiLibrary.model.entity.User;
import net.springBootApp.apiLibrary.repository.BookRepository; 
import net.springBootApp.apiLibrary.repository.BorrowRepository;
import net.springBootApp.apiLibrary.repository.UserRepository;
import net.springBootApp.apiLibrary.services.service.BorrowService;
import net.springBootApp.apiLibrary.validator.BookValidator;
import net.springBootApp.apiLibrary.validator.BorrowValidator;
import net.springBootApp.apiLibrary.validator.UserValidator;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepositroy;

    @Autowired
    private BorrowRepository borrowRepository;

    private UserValidator userValidator;
    private BookValidator bookValidator;
    private BorrowValidator borrowValidator;
    private ResponseData<Object> responseData;
    private Book book;
    private List<Borrow> borrows;
    private Borrow borrow;
    private User user;


    @Override
    public ResponseData<Object> createBorrow(long id , BorrowDto requesDto) throws CustomNotFoundExc, Exception {
        // TODO Auto-generated method stub
     Optional<User> userOpt = userRepositroy.findById(id);
      userValidator.validateUserNotFound(userOpt);
      user = userOpt.get();
      book = bookRepository.findByTitle(requesDto.getBookName());
      bookValidator.validateBookNotFound(book);
      Optional<Borrow> borrowOpt = borrowRepository.findByBookAndBorrowed(book, true);  
      borrowValidator.validateBorrowBadRequest(borrowOpt);        
      borrow = new Borrow();
      borrow.setBook(book);
      borrow.setUser(user);
      borrowRepository.save(borrow);
      responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "add successfully", borrow);
      return responseData;
    }

    @Override
    public ResponseData<Object> getById(long id) throws CustomNotFoundExc, Exception {
     Optional<Borrow> borrowOpt = borrowRepository.findById(id);
      borrowValidator.validateBorrowNotFound(borrowOpt);
      borrow = borrowOpt.get();
      responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", borrow);
      // responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);

    return responseData;
    }

    @Override
    public ResponseData<Object> getBorrow(Boolean status) throws CustomNotFoundExc {
          if (status == null) {
            borrows = borrowRepository.findAll();
          } else {
            borrows = borrowRepository.findByBorrowed(status);
          }
          borrowValidator.validateListBorrowNotfound(borrows);
          responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", borrows);

          return responseData;
    }

    @Override
    public ResponseData<Object> deleteBorrow(Long id) throws CustomNotFoundExc, Exception {
     Optional<Borrow> borrowOpt = borrowRepository.findById(id);
    // bisa dikasih validasi kalau buku ada atau tidak
    borrowValidator.validateBorrowNotFound(borrowOpt);
        borrow = borrowOpt.get();
        // update namenya
        borrow.setBorrowed(false);
        // save
        borrowRepository.save(borrow);
        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Thanks for borrow", borrow);
    
        return responseData;
    }
    
}
