package stud.team.pwsbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stud.team.pwsbackend.domain.Category;
import stud.team.pwsbackend.dto.CategoryDto;
import stud.team.pwsbackend.mapper.CategoryMapper;
import stud.team.pwsbackend.repository.CategoryRepository;
import stud.team.pwsbackend.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.listCategoryToListDto(categories);
    }

    @Override
    public void deleteAllCategory() {
        categoryRepository.deleteAll();
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.map(categoryMapper::categoryToDto).orElse(null);
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.dtoToCategory(categoryDto);
        category = categoryRepository.save(category);
        return categoryMapper.categoryToDto(category);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
}
