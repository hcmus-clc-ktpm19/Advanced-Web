package org.hcmus.sakila.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.hcmus.sakila.exception.CategoryNotFoundException;
import org.hcmus.sakila.model.entity.Category;
import org.hcmus.sakila.repository.CategoryRepository;
import org.hcmus.sakila.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private CategoryRepository categoryRepository;

  @Override
  public Long saveCategory(Category category) {
    categoryRepository.saveAndFlush(category);

    return category.getCategoryId();
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void deleteCategoryById(Long id) {
    if(!categoryRepository.existsById(id)) {
      throw new CategoryNotFoundException("Category not found");
    }
    categoryRepository.deleteById(id);
  }

  @Override
  public Category getCategoryByName(String name) {
    return categoryRepository.findByName(name)
        .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
  }
}
