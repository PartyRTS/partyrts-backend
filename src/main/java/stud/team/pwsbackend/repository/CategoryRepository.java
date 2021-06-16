package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stud.team.pwsbackend.domain.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.idCategory in :ids")
    List<Category> findCategoriesIds(@Param("ids") Iterable<Long> ids);

}
