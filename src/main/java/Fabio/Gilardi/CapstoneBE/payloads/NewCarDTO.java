package Fabio.Gilardi.CapstoneBE.payloads;

import Fabio.Gilardi.CapstoneBE.enums.car.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewCarDTO(
        @NotEmpty(message = "picture is mandatory")
        String picture,

        @NotEmpty(message = "model is mandatory")
        String model,

        @NotNull(message = "price is mandatory")
        @Min(value = 1, message = "price must be more than 0")
        int price,

        @NotNull(message = "kilometers is mandatory")
        @Min(value = 0, message = "kilometers can't be less than 0")
        int kilometers,

        @NotNull(message = "power is mandatory")
        @Min(value = 1, message = "power must be more than 0")
        int power,

        @NotNull(message = "displacements is mandatory")
        @Min(value = 1, message = "displacements must be more than 0")
        int displacements,

        @NotNull(message = "fuel consumption is mandatory")
        @Min(value = 1, message = "fuel consumption must be more than 0")
        int fuelConsumption,

        @NotNull(message = "registration date is mandatory")
        LocalDate registrationDate,

        @NotNull(message = "brand is mandatory")
        Brand brand,

        @NotNull(message = "status is mandatory")
        Status status,

        @NotNull(message = "fuel is mandatory")
        Fuel fuel,

        @NotNull(message = "emission class is mandatory")
        EmissionClass emissionClass,

        @NotNull(message = "transmission type is mandatory")
        TransmissionType transmissionType,

        @NotNull(message = "door number is mandatory")
        DoorNumber doorNumber,

        @NotNull(message = "color is mandatory")
        Color color
) {
}
