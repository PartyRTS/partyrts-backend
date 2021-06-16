package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private Long idUser;

    @NotNull
    @NotEmpty
    @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")//FIXME
    private String email;

    @NotNull
    private String firstName;

    private String secondName;

    private String logoUrl;

    private LocalDate registrationDate;

    private LocalDate birthdayDate;

    private String description;

    @NotNull
    private String password;
}
