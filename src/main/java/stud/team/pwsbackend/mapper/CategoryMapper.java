package stud.team.pwsbackend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import stud.team.pwsbackend.domain.Category;
import stud.team.pwsbackend.dto.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CategoryMapper {

    Category dtoToCategory(CategoryDto categoryDto);

    CategoryDto categoryToDto(Category category);

    List<Category> listDtoToListCategory(List<CategoryDto> categoryDtoList);

    List<CategoryDto> listCategoryToListDto(List<Category> categoryList);
}
