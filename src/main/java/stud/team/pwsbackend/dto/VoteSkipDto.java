package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteSkipDto {

    private Long idVote;

    private Boolean closeVote;

    private Integer numberSkipVideo;
}
