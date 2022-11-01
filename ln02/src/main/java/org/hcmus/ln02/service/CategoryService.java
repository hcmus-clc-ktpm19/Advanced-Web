package org.hcmus.ln02.service;

import java.util.List;
import org.hcmus.ln02.model.entity.Category;

public interface CategoryService {
  Long saveCategory(Category category);
  void deleteCategoryById(Long id);
  Category getCategoryByName(String name);
  List<Category> getAllCategories();
}
