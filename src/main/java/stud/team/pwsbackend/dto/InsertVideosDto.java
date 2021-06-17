package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Video;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertVideosDto {

    private Long idInsertVideos;

    private Integer numberCurrentVideo;

    private Integer numberPrevVideo;

    private Integer numberNextVideo;
    @NotEmpty
    private Long idCurrentVideo;
    @NotEmpty
    private Long idStream;
}
