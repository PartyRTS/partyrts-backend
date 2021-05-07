package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.domain.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
