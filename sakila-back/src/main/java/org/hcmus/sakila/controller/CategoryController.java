package org.hcmus.sakila.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.hcmus.sakila.model.dto.CategoryDto;
import org.hcmus.sakila.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category", description = "Category API")
@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController extends AbstractApplicationController {

  private CategoryService categoryService;

  @Operation(summary = "Create new category", description = "Create new category with name and description. Return the created category's id")
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Long saveCategory(@RequestBody CategoryDto categoryDto) {
    return categoryService.saveCategory(applicationMapper.toCategoryEntity(categoryDto));
  }

  @Operation(summary = "Get all categories")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))),
  })
  @GetMapping
  public ResponseEntity<List<CategoryDto>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories().stream()
        .map(applicationMapper::toCategoryDto)
        .collect(Collectors.toList()));
  }

  @Operation(summary = "Get category by id", description = "Find category by its id then return the category's information")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found the category",
          content = {
              @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryDto.class))}
      )
  })
  @GetMapping("/{id}")
  public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(
        applicationMapper.toCategoryDto(categoryService.getCategoryById(id)));
  }

  @Operation(summary = "Delete category by id", description = "Delete a category by its id, id is required")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "The category has been deleted", content = @Content),
  })
  @Parameter(description = "ID of category to be deleted. ID must be a number", required = true, example = "1", name = "id")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
    categoryService.deleteCategoryById(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Update category", description = "Update category name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "The category has been updated", content = @Content),
  })
  @PutMapping
  public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
    return ResponseEntity.ok(applicationMapper.toCategoryDto(categoryService.updateCategory(applicationMapper.toCategoryEntity(categoryDto))));
  }

}
