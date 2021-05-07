package stud.team.pwsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
