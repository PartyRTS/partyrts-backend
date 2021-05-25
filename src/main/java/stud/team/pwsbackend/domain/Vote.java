package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vote")
@Data
public class Vote {

    @Id
    @Column(name = "id_vote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVote;

    @Column(name = "close_vote")
    private Boolean closeVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stream")
    private Stream stream;

    @OneToOne(mappedBy = "vote", cascade = CascadeType.ALL)
    private VoteAdd voteAdds;

    @OneToOne(mappedBy = "vote", cascade = CascadeType.ALL)
    private VoteSkip voteSkips;

    @OneToMany(mappedBy = "vote")
    private List<UserVote> UserVotes = new ArrayList<UserVote>();
}
