package net.springBootApp.apiLibrary.services.service;

import net.springBootApp.apiLibrary.model.dto.request.BookDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;

public interface CategoryService {
  ResponseData<Object> addCategory(BookDto request);

  ResponseData<Object> getCategory(Boolean status);

  ResponseData<Object> getCategoryById(long id);

  ResponseData<Object> updateCategory(long id, BookDto request);

  ResponseData<Object> deleteCategory(long id);
} 
