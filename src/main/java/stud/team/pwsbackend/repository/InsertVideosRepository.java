package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stud.team.pwsbackend.domain.InsertVideos;
import stud.team.pwsbackend.domain.User;

import java.util.List;
import java.util.Optional;

public interface InsertVideosRepository extends JpaRepository<InsertVideos, Long> {
    @Query("select i from InsertVideos i where i.stream.idStream = ?1 ")
    List<InsertVideos> findVideosByStream(Long stream);

    @Query("select i from InsertVideos i where i.stream.idStream = ?1 and i.numberCurrentVideo = ?2")
    Optional<InsertVideos> findByNumberCurrentVideo(Long stream,Integer numberCurrentVideo);

    @Query("select i from InsertVideos i where i.stream.idStream = ?1 and i.numberPrevVideo = ?2")
    Optional<InsertVideos> findByNumberPrevVideo(Long stream, Integer numberPrevVideo);

    @Query("select i from InsertVideos i where i.stream.idStream = ?1 and i.numberNextVideo = ?2")
    Optional<InsertVideos> findByNumberNextVideo(Long stream,Integer numberNextVideo);
}
