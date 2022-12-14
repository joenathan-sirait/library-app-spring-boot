package net.springBootApp.apiLibrary.services.service;

import net.springBootApp.apiLibrary.model.dto.request.BookDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;

public interface BookService {
  // Proses CRUD
  ResponseData<Object> createBook(BookDto requesDto);

  ResponseData<Object> getAll();

  ResponseData<Object> getById(long id);

  ResponseData<Object> updateBook(long id, BookDto request);
  
  ResponseData<Object> deleteBook(long id);

}