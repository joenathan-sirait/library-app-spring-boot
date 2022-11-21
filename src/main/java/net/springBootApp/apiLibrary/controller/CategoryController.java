package net.springBootApp.apiLibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.springBootApp.apiLibrary.model.dto.request.BookDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.services.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  private ResponseData<Object> responseData;

  @GetMapping
  public ResponseEntity<Object> getAllCategories(@RequestParam(value = "status", defaultValue = "") Boolean status) {
    responseData = categoryService.getCategory(status);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> getCategory(@PathVariable long id) {
    responseData = categoryService.getCategoryById(id);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PostMapping
  public ResponseEntity<Object> addCategory(@RequestBody BookDto request) {
    responseData = categoryService.addCategory(request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> updateCategory(@PathVariable long id, @RequestBody BookDto request) {
    responseData = categoryService.updateCategory(id, request);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteCategory(@PathVariable long id) {
    responseData = categoryService.deleteCategory(id);
    return ResponseEntity.status(responseData.getStatus()).body(responseData);
  }
}