package Fabio.Gilardi.CapstoneBE.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewUserDTO(
        @NotEmpty(message = "username is mandatory")
        @Size(min = 4, message = "username must be longer than 3 characters")
        String username,

        @NotEmpty(message = "email is mandatory")
        @Email
        String email,

        @NotEmpty(message = "password is mandatory")
        @Size(min = 8, message = "password must be longer than 7 characters")
        String password,

        @NotEmpty(message = "name is mandatory")
        @Size(min = 4, message = "name must be longer than 3 characters")
        String name,

        @NotEmpty(message = "surname is mandatory")
        @Size(min = 4, message = "surname must be longer than 3 characters")
        String surname,

        @NotNull(message = "birth date is mandatory")
        LocalDate birthDate
) {
}
