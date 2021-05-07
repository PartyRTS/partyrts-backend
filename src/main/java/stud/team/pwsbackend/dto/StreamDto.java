package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreamDto {

    private Long idStream;

    private String streamTitle;

    private Boolean privateStream;

    private Integer fullUsers;

    private Integer currentNumberVideo;
}
