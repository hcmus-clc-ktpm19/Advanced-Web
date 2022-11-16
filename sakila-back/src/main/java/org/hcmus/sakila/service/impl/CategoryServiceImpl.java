package org.hcmus.sakila.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.exception.CategoryNotFoundException;
import org.hcmus.sakila.model.entity.Category;
import org.hcmus.sakila.repository.CategoryRepository;
import org.hcmus.sakila.service.CategoryService;
import org.hcmus.sakila.util.HmacUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(rollbackFor = Throwable.class)
@AllArgsConstructor
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  @Value("${system-auth.http.auth-header-name}")
  private String principalRequestHeader;

  @Value("${system-auth.http.time-header-name}")
  private String timeRequestHeader;

  @Value("${system-auth.http.secret-key}")
  private String principalRequestValue;

  private CategoryRepository categoryRepository;

  @Override
  public Long saveCategory(Category category) {
    categoryRepository.saveAndFlush(category);

    return category.getCategoryId();
  }

  @Override
  public List<Category> getAllCategories() {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.add(timeRequestHeader, LocalDateTime.now().toString());
    HmacUtil hmacUtil = new HmacUtil(Constants.HMAC_ALGORITHMS, principalRequestValue, "/api/v1/categories" + Objects.requireNonNull(
        headers.get(timeRequestHeader)).get(0));
    headers.add(principalRequestHeader, hmacUtil.hmacWithApacheCommons());
    HttpEntity<Object> request = new HttpEntity<>(headers);
    ResponseEntity<Object[]> response = restTemplate.exchange(Constants.SAKILA_SERVICE_URL + "/categories", HttpMethod.GET, request, Object[].class);
    Object[] objects = response.getBody();
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
    return Arrays.stream(Objects.requireNonNull(objects))
        .map(object -> mapper.convertValue(object, Category.class))
        .collect(Collectors.toList());
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
