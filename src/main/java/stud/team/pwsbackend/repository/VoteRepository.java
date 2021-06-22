package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stud.team.pwsbackend.domain.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query("select v from Vote v where v.stream.idStream = ?1 and v.closeVote = false")
    Optional<Vote> findActiveVoteByStream(Long streamId);
}
