package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stream")
@Data
public class Stream {

    @Id
    @Column(name = "id_stream")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStream;

    @Column(name = "stream_title")
    private String streamTitle;

    @Column(name = "private_stream")
    private Boolean privateStream;

    @Column(name = "full_users")
    private Integer fullUsers;

    @Column(name = "current_number_video")
    private Integer currentNumberVideo;

    @OneToOne(mappedBy = "stream", cascade = CascadeType.ALL)
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_playlist")
    private Playlist playlist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stream_has_category", joinColumns = @JoinColumn(name = "stream_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stream_has_user", joinColumns = @JoinColumn(name = "stream_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "stream")
    private List<InsertVideos> insertVideos = new ArrayList<InsertVideos>();

    @OneToMany(mappedBy = "stream")
    private List<Message> messages = new ArrayList<Message>();
}
