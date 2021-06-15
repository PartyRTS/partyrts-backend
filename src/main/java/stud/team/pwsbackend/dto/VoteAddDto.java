package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Video;
import stud.team.pwsbackend.domain.Vote;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteAddDto {

    private Long idVote;

    private Integer numberPrevVideo;

    private Long idIVote;

    private Long idAddVideo;
}
