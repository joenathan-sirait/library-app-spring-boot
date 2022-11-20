package net.springBootApp.apiLibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.springBootApp.apiLibrary.model.entity.Category;



@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
  List<Category> findByIsDeleted(boolean deleted);

  Category findByName(String name);
}