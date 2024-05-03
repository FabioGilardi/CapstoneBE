package Fabio.Gilardi.CapstoneBE.payloads;

import jakarta.validation.constraints.*;

public record NewReviewDTO(
        @NotEmpty(message = "title is mandatory")
        @Size(min = 4, message = "title must be longer than 3 characters")
        String title,

        @NotEmpty(message = "description is mandatory")
        String description,

        @NotNull(message = "rating is mandatory")
        @Min(value = 0, message = "rating can't be less than 0")
        @Max(value = 5, message = "rating can't be more than 5")
        int rating
) {
}
