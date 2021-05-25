package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "userVote")
@Data
public class UserVote {

    @Id
    @Column(name = "id_userVote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUserVote;

    @Column(name = "vote_plus")
    private Boolean votePlus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vote")
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user")
    private User user;
}
