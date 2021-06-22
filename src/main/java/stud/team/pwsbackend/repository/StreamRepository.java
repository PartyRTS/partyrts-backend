package stud.team.pwsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import stud.team.pwsbackend.domain.Stream;

import java.util.List;

public interface StreamRepository extends JpaRepository<Stream, Long> {

    List<Stream> findByStreamTitleContainingIgnoreCase(String streamTitle);

    @Query(value = "select sum(s.full_users) + 1 as full, sum(cnt) + 1 as auth, s.create_date as date \n" +
            "from stream s \n" +
            "left join ( select count(u.user_id) as cnt , u.stream_id as id \n" +
            "\t\t   from stream_has_user u group by u.stream_id) u1 on \n" +
            "\t\t   u1.id = s.id_stream\n" +
            "group by s.create_date ", nativeQuery = true)
    List<Object []> findCountUserOnStream();

    @Query(value = "select count(s), s.createDate \n" +
            "from Stream s \n" +
            "group by s.createDate ")
    List<Object []> findCountStreams();

    @Query(value = "select sum(cnt) as sumvote,count(s) as streamcount, s.create_date as date  \n" +
            "from stream s \n" +
            "left join ( select count(v) as cnt , v.fk_stream as idv \n" +
            "\t\t   from vote v group by v.fk_stream) v1 on \n" +
            "\t\t   v1.idv = s.id_stream\n" +
            "group by s.create_date ", nativeQuery = true)
    List<Object []> findCountVoteOnStream();
}
