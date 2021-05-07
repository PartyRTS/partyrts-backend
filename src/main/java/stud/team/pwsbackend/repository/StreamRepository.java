package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.Stream;

public interface StreamRepository extends JpaRepository<Stream, Long> {
}
