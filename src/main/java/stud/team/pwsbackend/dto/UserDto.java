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
public class UserDto {
    private Long idUser;
    @NotNull
    @ExtendedEmail
    private String email;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String secondName;

    private String logoUrl;

    private Boolean banned;

    private LocalDate registrationDate;

    private LocalDate birthdayDate;

    private String description;

}
