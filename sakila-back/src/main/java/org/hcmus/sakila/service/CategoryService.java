package org.hcmus.sakila.service;

import java.util.List;
import org.hcmus.sakila.model.entity.Category;

public interface CategoryService {
  Long saveCategory(Category category);
  void deleteCategoryById(Long id);
  Category getCategoryById(Long id);
  List<Category> getAllCategories();
  Category updateCategory(Category category);
}
