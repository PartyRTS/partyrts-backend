package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.ExtendedEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private Long idUser;

    @NotNull
    @ExtendedEmail
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String secondName;

    @NotNull
    private LocalDate birthdayDate;

    private String description;

    @NotEmpty
    private String password;
}
