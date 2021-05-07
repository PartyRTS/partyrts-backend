package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
