package ru.practicum.explorewithme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explorewithme.model.category.Category;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    @Override
    public Category getCategoryById(Long catId) {
        return null;
    }

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        return null;
    }
}
