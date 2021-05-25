package stud.team.pwsbackend.service;

import stud.team.pwsbackend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategory();

    void deleteAllCategory();

    CategoryDto getCategoryById(Long categoryId);

    CategoryDto addCategory(CategoryDto categoryDto);

    void deleteCategoryById(Long categoryId);
}
