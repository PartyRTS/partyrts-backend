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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Stream stream;

    @OneToMany(mappedBy = "vote")
    private List<VoteAdd> voteAdds = new ArrayList<VoteAdd>();

    @OneToMany(mappedBy = "vote")
    private List<VoteSkip> voteSkips = new ArrayList<VoteSkip>();
}
