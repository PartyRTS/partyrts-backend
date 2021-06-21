package stud.team.pwsbackend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String secondName;

    private String logoUrl;

    @Column(name = "banned")
    private Boolean banned = false;

    @Column(nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    @Column(nullable = false)
    private LocalDate birthdayDate;

    private String description;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Password password;

    @ManyToMany(mappedBy = "users")
    private List<Stream> streams;

    @ManyToMany
    @JoinTable(
            name = "user_has_friends",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_friend"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> friends = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_has_friend_request",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_sender"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<User> friendRequest = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "user_has_global_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_global_role"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<GlobalRole> globalRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Stream> usersStreams = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Playlist> userPlaylists = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Video> userVideos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserVote> userVotes = new ArrayList<>();
}
