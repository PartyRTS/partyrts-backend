package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
