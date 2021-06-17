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
public class PlaylistDto {

    private Long idPlaylist;

    private String title;

    @NotEmpty
    private Long idUser;
}
