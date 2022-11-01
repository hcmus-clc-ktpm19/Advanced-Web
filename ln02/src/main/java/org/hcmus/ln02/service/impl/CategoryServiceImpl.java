package org.hcmus.ln02.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.hcmus.ln02.exception.CategoryNotFoundException;
import org.hcmus.ln02.model.entity.Category;
import org.hcmus.ln02.repository.CategoryRepository;
import org.hcmus.ln02.service.CategoryService;
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
