package stud.team.pwsbackend.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "insertVideos")
@Data
public class InsertVideos {

    @Id
    @Column(name = "id_insert_videos")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInsertVideos;

    @Column(name = "number_current_video")
    private Integer numberCurrentVideo;

    @Column(name = "number_prev_video")
    private Integer numberPrevVideo;

    @Column(name = "number_next_video")
    private Integer numberNextVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_current_video")
    private Video currentVideo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_stream")
    private Stream stream;
}
