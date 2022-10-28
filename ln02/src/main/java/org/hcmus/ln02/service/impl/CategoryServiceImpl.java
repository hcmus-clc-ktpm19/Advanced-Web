package org.hcmus.ln02.service.impl;

import lombok.AllArgsConstructor;
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
}
