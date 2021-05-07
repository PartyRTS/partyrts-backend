package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
