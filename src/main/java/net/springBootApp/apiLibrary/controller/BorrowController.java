package net.springBootApp.apiLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.dto.request.BorrowDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.services.service.BorrowService;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    private ResponseData<Object> responseData;
    
    @GetMapping
    public ResponseEntity<Object> getAllborrow(@RequestParam(value = "status", defaultValue = "") Boolean status) throws CustomNotFoundExc {
        responseData = borrowService.getBorrow(status);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
      }
  
    @PostMapping("/addborrow/{id}")
    public ResponseEntity<Object> postBook(@PathVariable long id, @RequestBody BorrowDto request) throws CustomNotFoundExc, Exception {
      responseData = borrowService.createBorrow(id,request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) throws CustomNotFoundExc , Exception{
      responseData = borrowService.getById(id);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBorrow(@PathVariable long id) throws CustomNotFoundExc, Exception {
      responseData = borrowService.deleteBorrow(id);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
