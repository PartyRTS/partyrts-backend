package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.VideoHasPlaylist;

public interface VideoHasPlaylistRepository extends JpaRepository<VideoHasPlaylist, Long> {
}
