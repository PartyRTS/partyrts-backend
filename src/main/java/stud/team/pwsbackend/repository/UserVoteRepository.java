package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.UserVote;

public interface UserVoteRepository extends JpaRepository<UserVote, Long> {
}
