package stud.team.pwsbackend.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "global_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGlobalRole;

    @Column(unique = true, nullable = false)
    private String title;
}
