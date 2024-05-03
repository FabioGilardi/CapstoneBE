package Fabio.Gilardi.CapstoneBE.payloads;

import Fabio.Gilardi.CapstoneBE.enums.car.Color;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateCarDTO(
        @NotEmpty(message = "picture is mandatory")
        String picture,

        @NotNull(message = "price is mandatory")
        @Min(value = 1, message = "price must be more than 0")
        int price,

        @NotNull(message = "color is mandatory")
        Color color
) {
}
