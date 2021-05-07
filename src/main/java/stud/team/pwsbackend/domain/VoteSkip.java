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

    @Column(name = "close_vote")
    private Boolean closeVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_vote")
    private Vote vote;

    @Column(name = "number_skip_video")
    private Integer numberSkipVideo;
}
