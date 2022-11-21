package net.springBootApp.apiLibrary.validator;

import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.entity.Book;

public class BookValidator {
    public void validateBookNotFound(Book book) throws Exception{
        if (book == null) {
            throw new CustomNotFoundExc("book not found");
          } 
    }
}
