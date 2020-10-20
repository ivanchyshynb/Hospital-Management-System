package com.hospital.service;

import com.hospital.dao.CategoryDao;
import com.hospital.model.Category;

import java.util.List;

public class CategoryService {
    CategoryDao categoryDao;

    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    public boolean update(Category category) {
        return categoryDao.update(category);
    }

    public boolean delete(Category category) {
        return categoryDao.delete(category);
    }

    public boolean deleteById(int id) {
        return categoryDao.deleteById(id);
    }

    public Category create(Category category){
        return categoryDao.create(category);
    }

    public Category getById(int id){
        return categoryDao.getById(id);
    }
}
