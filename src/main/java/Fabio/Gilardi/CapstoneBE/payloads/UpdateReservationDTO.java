package Fabio.Gilardi.CapstoneBE.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UpdateReservationDTO(
        @NotNull(message = "reservation date is mandatory")
        LocalDateTime reservationDate
) {
}
