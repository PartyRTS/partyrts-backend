package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stud.team.pwsbackend.domain.UserVote;

import java.util.List;

public interface UserVoteRepository extends JpaRepository<UserVote, Long> {

    @Query(value = "select count(u) from UserVote u where u.vote.idVote = ?1 and u.votePlus = true group by u.votePlus")
    Long findCountPlusVotes(Long vote);
}
