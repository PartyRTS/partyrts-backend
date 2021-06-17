package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import stud.team.pwsbackend.domain.Stream;
import stud.team.pwsbackend.domain.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long idMessage;
    @NotEmpty
    private Long text;
    @NotEmpty
    private Long idUser;
    @NotEmpty
    private Long idStream;
}
