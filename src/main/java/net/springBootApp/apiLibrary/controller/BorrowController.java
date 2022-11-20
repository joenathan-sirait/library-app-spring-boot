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

import net.springBootApp.apiLibrary.model.dto.BorrowDto;
import net.springBootApp.apiLibrary.model.dto.ResponseData;
import net.springBootApp.apiLibrary.service.BorrowService;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    private ResponseData<Object> responseData;
    
    @GetMapping
    public ResponseEntity<Object> getAllborrow(@RequestParam(value = "status", defaultValue = "") Boolean status) {
        responseData = borrowService.getBorrow(status);
        return ResponseEntity.status(responseData.getStatus()).body(responseData);
      }
  
    @PostMapping
    public ResponseEntity<Object> postBook(@RequestBody BorrowDto request) {
      responseData = borrowService.createBorrow(request);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
  
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
      responseData = borrowService.getById(id);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBorrow(@PathVariable long id) {
      responseData = borrowService.deleteBorrow(id);
      return ResponseEntity.status(responseData.getStatus()).body(responseData);
    }
}
