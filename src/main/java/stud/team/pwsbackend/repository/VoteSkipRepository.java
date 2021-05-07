package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.VoteSkip;

public interface VoteSkipRepository extends JpaRepository<VoteSkip, Long> {
}
