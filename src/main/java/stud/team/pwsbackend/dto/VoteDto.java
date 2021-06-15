package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Stream;
import stud.team.pwsbackend.domain.VoteAdd;
import stud.team.pwsbackend.domain.VoteSkip;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDto {

    private Long idVote;

    private Boolean closeVote;

    private Long idStream;

    private Long idVoteAdds;

    private Long idVoteSkips;

}
