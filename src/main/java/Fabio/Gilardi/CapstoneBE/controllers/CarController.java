package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.entities.Car;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.NewCarDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdateCarDTO;
import Fabio.Gilardi.CapstoneBE.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('SELLER')")
    public Car save(@RequestBody @Validated NewCarDTO payload,
                    BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.carService.save(payload);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SELLER')")
    public Car findByIdAndUpdate(@PathVariable long id,
                                 @RequestBody @Validated UpdateCarDTO payload,
                                 BindingResult validation
    ) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.carService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('SELLER')")
    public void findByIdAndDelete(@PathVariable long id) {
        this.carService.findByIdAndDelete(id);
    }
}
