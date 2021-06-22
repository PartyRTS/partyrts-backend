package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stud.team.pwsbackend.domain.Stream;

import java.util.List;

public interface StreamRepository extends JpaRepository<Stream, Long> {

    List<Stream> findByStreamTitleContainingIgnoreCase(String streamTitle);

    @Query(value = "select sum(s.fullUsers),sum(count(u)), s.createDate from Stream s " +
            "left join s.users u " +
            "group by s.createDate")
    List<Object []> findCountUserOnStream();
}
