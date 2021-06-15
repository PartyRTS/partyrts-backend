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
public class PlaylistDto {

    private Long idPlaylist;

    private String title;

    private Long idUser;
}
