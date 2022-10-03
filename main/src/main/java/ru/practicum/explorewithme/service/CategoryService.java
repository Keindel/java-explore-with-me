package ru.practicum.explorewithme.service;

import ru.practicum.explorewithme.exceptions.notfound.CategoryNotFoundException;
import ru.practicum.explorewithme.model.category.Category;

import java.util.List;

public interface CategoryService {

    Category getCategoryById(Long catId) throws CategoryNotFoundException;

    List<Category> getCategories(Integer from, Integer size);
}
