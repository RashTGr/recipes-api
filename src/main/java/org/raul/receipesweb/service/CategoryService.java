package org.raul.receipesweb.service;

import lombok.extern.slf4j.Slf4j;
import org.raul.receipesweb.exception.ResourceNotFoundException;
import org.raul.receipesweb.model.Category;
import org.raul.receipesweb.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private final CategoryRepository categoryRepository;

    // Constructor injection : dependency injection through a constr.
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // implement DTO logic here!!!
    public List<Category> getAllCategories() {
        log.info("Fetching categories from the db");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        log.info("Fetching category by id {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found category with id :: " + id));
    }

    public Category addCategory(Category category) {
        log.info("Adding new category: {}", category.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryName) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found category with id :: " + id));

        category.setName(categoryName.getName());
        log.info("Updated category by id {} and name: {}", id, categoryName.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        log.debug("Deleting category id {} from db", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't found category with id :: " + id));
        log.info("Deleted category {} ", id);
        categoryRepository.delete(category);
    }
}
