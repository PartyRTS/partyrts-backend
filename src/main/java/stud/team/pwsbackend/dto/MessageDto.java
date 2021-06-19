package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto {
    private Long idMessage;
    @NotEmpty
    private String text;
    @NotNull
    private Long idUser;
    @NotNull
    private Long idStream;
}
