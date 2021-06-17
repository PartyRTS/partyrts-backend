package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.User;

import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VideoDto {

    private Long idVideo;

    private String title;

    private String videoUrl;

    private String previewUrl;
    @NotEmpty
    private Long idUser;
}
