package stud.team.pwsbackend.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "password")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPassword;

    @Column(nullable = false)
    private String passwordHash;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
