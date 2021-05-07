package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "videoHasPlaylist")
@Data
public class VideoHasPlaylist {

    @Id
    @Column(name = "id_video_has_playlist")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideoHasPlaylist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "number_video")
    private Integer numberVideo;
}
