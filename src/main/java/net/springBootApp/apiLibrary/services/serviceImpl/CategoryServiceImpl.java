package net.springBootApp.apiLibrary.services.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import net.springBootApp.apiLibrary.model.dto.request.BookDto;
import net.springBootApp.apiLibrary.model.dto.response.ResponseData;
import net.springBootApp.apiLibrary.model.entity.Category;
import net.springBootApp.apiLibrary.repository.CategoryRepository;
import net.springBootApp.apiLibrary.services.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  private Category category;
  private ResponseData<Object> responseData;
  private List<Category> categories;

  @Override
  public ResponseData<Object> addCategory(BookDto request) {
    // TODO Auto-generated method stub
    category = new Category();
    category.setName(request.getCategoryName());
    categoryRepository.save(category);

    responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Add category success", category.getName());
    return responseData;
  }

  @Override
  public ResponseData<Object> getCategory(Boolean status) {
    // TODO Auto-generated method stub
    // validasi statusnya, user mengirimkan data status?
    if (status == null) {
      categories = categoryRepository.findAll();
    } else {
      categories = categoryRepository.findByIsDeleted(status);
    }

    // bisa dikasih validasi kalau list category masih kosong, belum ada data
    if (categories.isEmpty()) {
    responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "Data Is Empty", null);
        
    } else {
    responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", categories);
        
    }
    // category
    return responseData;
  }

  @Override
  public ResponseData<Object> updateCategory(long id, BookDto request) {
    // TODO Auto-generated method stub
    // dicari berdasarkan id category untuk diupdate namenya
    Optional<Category> categoryOpt = categoryRepository.findById(id);
    // bisa dikasih validasi kalau buku ada atau tidak
    if (categoryOpt.isPresent()) {
        category = categoryOpt.get();
    // update namenya
    category.setName(request.getCategoryName());
    // save
    categoryRepository.save(category);
    // response data
    responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Update success", category);
    } else {
    responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "data not found", null);
        
    }
    
    return responseData;
  }

  @Override
  public ResponseData<Object> deleteCategory(long id) {
    // TODO Auto-generated method stub
    // dicari berdasarkan id category untuk diupdate namenya
    Optional<Category> categoryOpt = categoryRepository.findById(id);
    // bisa dikasih validasi kalau buku ada atau tidak
    if (categoryOpt.isPresent()) {
        category = categoryOpt.get();
        // update namenya
        category.setDeleted(true);
        // save
        categoryRepository.save(category);

        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Delete success", null);
    } else {
        responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "data not found", null);
        
    }
    
    return responseData;
  }

  @Override
  public ResponseData<Object> getCategoryById(long id) {
    // TODO Auto-generated method stub
    // dicari berdasarkan id category untuk diupdate namenya
    Optional<Category> categoryOpt = categoryRepository.findById(id);
    // bisa dikasih validasi kalau buku ada atau tidak
    if (categoryOpt.isPresent()) {
        category = categoryOpt.get();

        // response data
        responseData = new ResponseData<Object>(200, "success", category);
    } else {
        responseData = new ResponseData<Object>(404, "failed", null);
        
    }

    return responseData;
  }

}
