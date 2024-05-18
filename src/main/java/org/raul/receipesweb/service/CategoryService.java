package org.raul.receipesweb.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.dto.CategoryDTO;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Category;
import org.raul.receipesweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategories() {
        log.info("Fetching categories from the db");

        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> new CategoryDTO(
                category.getId(),
                category.getName()
        )).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        log.info("Fetching category by id {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found category with id :: " + id));

        return new CategoryDTO(category.getId(), category.getName());
    }

    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        log.info("Adding new category: {}", categoryDTO.getName());

        Category cat = new Category();
        cat.setName(categoryDTO.getName());
        Category saved = categoryRepository.save(cat);
        log.info("Added new category with ID: {}", saved.getId());

        return convertToDTO(saved);
    }

    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        log.info("Updating category with ID: {}", id);

        Category cat = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found category with id :: " + id));
        cat.setName(categoryDTO.getName());
        Category updated = categoryRepository.save(cat);
        log.info("Updated category ID: {} with new name: {}", id, categoryDTO.getName());

        return convertToDTO(updated);
    }

    public void deleteCategory(Long id) {
        log.info("Deleting category with ID: {}", id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found category with id :: " + id));
        categoryRepository.delete(category);
        log.info("Deleted category with ID: {}", id);
    }

    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
