package stud.team.pwsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stud.team.pwsbackend.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select u from User u where (upper(u.firstName) like concat('%', upper(search), '%')) or" +
            "(upper(u.secondName) like concat('%', upper(search), '%'))")
    List<User> findUserByName(@Param("search")String searchString);
}
