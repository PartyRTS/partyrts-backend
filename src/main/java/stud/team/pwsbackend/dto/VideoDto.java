package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDto {

    private Long idVideo;

    private String title;

    private String videoUrl;

    private String previewUrl;

    private Long idUser;
}
