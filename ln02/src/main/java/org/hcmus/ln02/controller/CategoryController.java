package org.hcmus.ln02.controller;

import lombok.AllArgsConstructor;
import org.hcmus.ln02.model.dto.CategoryDto;
import org.hcmus.ln02.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController extends AbstractApplicationController {

  private CategoryService categoryService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long saveCategory(@RequestBody CategoryDto categoryDto) {
    return categoryService.saveCategory(applicationMapper.toCategoryEntity(categoryDto));
  }
}
