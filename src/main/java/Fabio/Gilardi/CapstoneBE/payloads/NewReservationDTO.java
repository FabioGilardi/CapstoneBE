package Fabio.Gilardi.CapstoneBE.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record NewReservationDTO(
        @NotNull(message = "reservation date is mandatory")
        LocalDateTime reservationDate,

        @NotNull(message = "car id is mandatory")
        @Min(value = 1, message = "car id must be more than 0")
        long carId,

        @NotNull(message = "client id is mandatory")
        @Min(value = 1, message = "client id must be more than 0")
        long sellerId
) {
}
