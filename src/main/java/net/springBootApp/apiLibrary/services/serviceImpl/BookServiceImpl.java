package net.springBootApp.apiLibrary.services.serviceImpl;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.springBootApp.apiLibrary.model.dto.request.BookDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.model.entity.Book;
import net.springBootApp.apiLibrary.model.entity.Category;
import net.springBootApp.apiLibrary.repository.BookRepository;
import net.springBootApp.apiLibrary.repository.CategoryRepository;
import net.springBootApp.apiLibrary.services.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private BookRepository bookRepository;
  private ResponseData<Object> responseData;
  private Book book;
  private List<Book> books;
  private Category category;

  @Override
  public ResponseData<Object> createBook(BookDto requesDto) {
    // TODO Auto-generated method stub
    book = new Book(requesDto.getTitle(), requesDto.getAuthor());
    category = categoryRepository.findByName(requesDto.getCategoryName());
    // save to db
    book.setCategory(category);
    bookRepository.save(book);

    // response data
    responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "add successfully", book);
    return responseData;
  }

  @Override
  public ResponseData<Object> getAll() {
    // TODO Auto-generated method stub
    // find all book
    books = bookRepository.findAll();

    // response data
    responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", books);
    return responseData;
  }

  @Override
  public ResponseData<Object> getById(long id) {
    // TODO Auto-generated method stub
    Optional<Book> bookOpt = bookRepository.findById(id);
    if (bookOpt.isPresent()) {
      book = bookOpt.get();
      responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", book);
    } else {
      responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
    }
    return responseData;
  }

  @Override
  public ResponseData<Object> updateBook(long id, BookDto request) {
    // TODO Auto-generated method stub
    Optional<Book> bookOpt = bookRepository.findById(id);
    if (bookOpt.isPresent()) {
      book = bookOpt.get();
      // update buku
      book.setTitle(request.getTitle());
      book.setAuthor(request.getAuthor());
      category = categoryRepository.findByName(request.getCategoryName());
      book.setCategory(category);
      // save
      bookRepository.save(book);

      // response data
      responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", book);
    } else {
      responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
    }
    return responseData;
  }

  @Override
  public ResponseData<Object> deleteBook(long id) {
    // TODO Auto-generated method stub
    Optional<Book> bookOpt = bookRepository.findById(id);
    if (bookOpt.isPresent()) {
      book = bookOpt.get();

      // delete / change property isDeleted
      book.setDeleted(true);

      // save
      bookRepository.save(book);

      // response data
      responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", null);
    } else {
      responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
    }
    return responseData;
  }

}
