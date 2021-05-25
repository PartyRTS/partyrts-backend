package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "voteSkip")
@Data
public class VoteSkip {

    @Id
    @Column(name = "id_vote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVote;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Vote vote;

    @Column(name = "number_skip_video")
    private Integer numberSkipVideo;
}
