package stud.team.pwsbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utils.ExtendedEmail;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {
    @NotEmpty
    @ExtendedEmail
    private String email;

    @NotEmpty
    private String password;
}
