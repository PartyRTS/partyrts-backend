package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;


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

    private Boolean stopStream;

    private LocalTime timeStream;

    private Integer fullUsers;

    private Long currentNumberVideo;
    @NotNull
    private Long idPlaylist;

    private Long idUser;
}
