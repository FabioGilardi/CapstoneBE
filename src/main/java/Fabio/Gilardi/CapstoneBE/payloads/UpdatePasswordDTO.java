package Fabio.Gilardi.CapstoneBE.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDTO(
        @NotEmpty(message = "old password is mandatory")
        String oldPassword,

        @NotEmpty(message = "new password is mandatory")
        @Size(min = 8, message = "password must be longer than 7 characters")
        String newPassword
) {
}
