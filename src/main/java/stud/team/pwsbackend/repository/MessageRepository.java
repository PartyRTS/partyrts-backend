package stud.team.pwsbackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import stud.team.pwsbackend.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
