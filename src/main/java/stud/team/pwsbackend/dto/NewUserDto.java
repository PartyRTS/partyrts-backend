package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserDto {
    private Long idUser;

    private String email;

    private String firstName;

    private String secondName;

    private String logoUrl;

    private LocalDate registrationDate;

    private LocalDate birthdayDate;

    private String description;

    private String password;
}
