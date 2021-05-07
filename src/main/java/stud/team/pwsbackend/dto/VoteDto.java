package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Stream;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDto {

    private Long idVote;

    private Stream stream;
}
