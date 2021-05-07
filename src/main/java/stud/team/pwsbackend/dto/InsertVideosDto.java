package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertVideosDto {

    private Long idInsertVideos;

    private Integer numberCurrentVideo;

    private Integer numberPrevVideo;

    private Integer numberNextVideo;
}
