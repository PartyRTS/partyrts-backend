package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "video")
@Data
public class Video {

    @Id
    @Column(name = "id_video")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVideo;

    @Column(name = "title")
    private String title;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "preview_url")
    private String previewUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

    @OneToMany(mappedBy = "addVideo")
    private List<VoteAdd> addVideos = new ArrayList<VoteAdd>();

    @OneToMany(mappedBy = "currentVideo")
    private List<InsertVideos> currentVideos = new ArrayList<InsertVideos>();

    @OneToMany(mappedBy = "video")
    private List<VideoHasPlaylist> videoHasPlaylists = new ArrayList<VideoHasPlaylist>();

}
