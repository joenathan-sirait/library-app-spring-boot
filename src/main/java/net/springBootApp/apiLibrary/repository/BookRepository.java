package net.springBootApp.apiLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootApp.apiLibrary.model.entity.Book;



@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
}