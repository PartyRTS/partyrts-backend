package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
@Data
public class Playlist {

    @Id
    @Column(name = "id_playlist")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlaylist;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

    @OneToMany(mappedBy = "playlist")
    private List<Stream> streams = new ArrayList<Stream>();

    @OneToMany(mappedBy = "playlist")
    private List<VideoHasPlaylist> videoHasPlaylists = new ArrayList<VideoHasPlaylist>();
}
