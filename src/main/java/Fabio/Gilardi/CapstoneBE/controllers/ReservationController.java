package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.entities.Reservation;
import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.NewReservationDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdateReservationDTO;
import Fabio.Gilardi.CapstoneBE.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Reservation> findAll(@RequestParam(defaultValue = "0") int number,
                                     @RequestParam(defaultValue = "50") int size,
                                     @RequestParam(defaultValue = "id") String sortBy) {
        return this.reservationService.findAll(number, size, sortBy);
    }

    @GetMapping("/me")
    public List<Reservation> findMyReservations(@AuthenticationPrincipal User currentClient) {
        return this.reservationService.findAllByClientId(currentClient.getId());
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable long id) {
        return this.reservationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@AuthenticationPrincipal User currentClient,
                            @RequestBody @Validated NewReservationDTO payload,
                            BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.reservationService.save(currentClient.getId(), payload);
    }

    @PutMapping("/{id}")
    public Reservation findByIdAndUpdate(@PathVariable long id,
                                         @RequestBody @Validated UpdateReservationDTO payload,
                                         BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.reservationService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.reservationService.findByIdAndDelete(id);
    }
}
