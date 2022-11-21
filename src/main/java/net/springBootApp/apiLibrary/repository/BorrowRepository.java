package net.springBootApp.apiLibrary.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootApp.apiLibrary.model.entity.Book;
import net.springBootApp.apiLibrary.model.entity.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long> {
  List<Borrow> findByBorrowed(boolean borrowed);
  Optional<Borrow> findByBookAndBorrowed(Book book , Boolean borrowed);

    
}
