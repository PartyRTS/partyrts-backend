package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StreamDto {

    private Long idStream;
    @NotEmpty
    private String streamTitle;

    private Boolean privateStream;

    private Boolean activeStream;

    private Integer fullUsers;

    private Long currentNumberVideo;
    @NotNull
    private Long idPlaylist;

    private Long idUser;
}
