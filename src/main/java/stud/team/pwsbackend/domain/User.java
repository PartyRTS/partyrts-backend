package stud.team.pwsbackend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "_user") //FIXME
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

    @Column(nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    @Column(nullable = false)
    private LocalDate birthdayDate;

    private String description;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Password password;

    @ManyToMany
    @JoinTable(
            name = "user_has_friends",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_friend"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> friends = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_has_friend_request",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_sender")) //TODO rename?
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> friendRequest = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_has_global_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_global_role"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<GlobalRole> globalRoles = new HashSet<>();
}
