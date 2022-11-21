package net.springBootApp.apiLibrary.validator;

import java.util.List;
import java.util.Optional;

import net.springBootApp.apiLibrary.exception.custom.CustomBadRequestExc;
import net.springBootApp.apiLibrary.exception.custom.CustomNotFoundExc;
import net.springBootApp.apiLibrary.model.entity.Borrow;

public class BorrowValidator {
    public void validateBorrowBadRequest(Optional<Borrow> borrowOpt) throws Exception{
        if (borrowOpt.isPresent()) {
            throw new CustomBadRequestExc("book not available");
          }   
    }

    public void validateBorrowNotFound( Optional<Borrow> borrowOpt) throws Exception {
        if (borrowOpt.isEmpty()) {
            throw new CustomNotFoundExc("Data Is Empty");
          }
    }
    public void validateListBorrowNotfound(List<Borrow> borrows) throws CustomNotFoundExc {
        if (borrows.isEmpty()) {
            throw new CustomNotFoundExc("Data Is Empty");
            } 
    }
}
