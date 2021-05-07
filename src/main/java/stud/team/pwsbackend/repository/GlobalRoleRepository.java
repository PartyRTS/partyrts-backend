package stud.team.pwsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.entity.GlobalRole;

import java.util.Optional;

public interface GlobalRoleRepository extends JpaRepository<GlobalRole, Long> {
    Optional<GlobalRole> findByTitle(String title);
}
