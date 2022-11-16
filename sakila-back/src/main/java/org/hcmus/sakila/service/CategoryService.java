package org.hcmus.sakila.service;

import java.util.List;
import org.hcmus.sakila.model.entity.Category;

public interface CategoryService {
  Long saveCategory(Category category);
  void deleteCategoryById(Long id);
  Category getCategoryByName(String name);
  List<Category> getAllCategories();
}
