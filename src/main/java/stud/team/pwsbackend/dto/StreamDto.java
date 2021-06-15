package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Playlist;
import stud.team.pwsbackend.domain.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


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

    private Long idPlaylist;

    private Long idUser;
}
