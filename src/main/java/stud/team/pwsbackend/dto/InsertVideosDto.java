package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Video;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertVideosDto {

    private Long idInsertVideos;

    private Integer numberCurrentVideo;

    private Integer numberPrevVideo;

    private Integer numberNextVideo;

    private Long idCurrentVideo;

    private Long idStream;
}
