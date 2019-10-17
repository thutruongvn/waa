package edu.mum.cs.waa.finalpractice.service;

import edu.mum.cs.waa.finalpractice.domain.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id);
}
