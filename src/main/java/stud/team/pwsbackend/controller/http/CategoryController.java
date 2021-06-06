package stud.team.pwsbackend.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stud.team.pwsbackend.dto.CategoryDto;
import stud.team.pwsbackend.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories",
        produces = "application/json")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @DeleteMapping
    public void deleteAllCategory() {
        categoryService.deleteAllCategory();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
    }
}
