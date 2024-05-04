package Fabio.Gilardi.CapstoneBE.services;

import Fabio.Gilardi.CapstoneBE.entities.Reservation;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.exceptions.NotFoundException;
import Fabio.Gilardi.CapstoneBE.payloads.NewReservationDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdateReservationDTO;
import Fabio.Gilardi.CapstoneBE.repositories.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationDAO reservationDAO;

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    public Reservation save(long clientId, NewReservationDTO payload) {
        if (this.reservationDAO.existsByClientIdAndReservationDate(clientId, payload.reservationDate()))
            throw new BadRequestException("You have already a reservation for this date");
        if (this.reservationDAO.existsBySellerIdAndReservationDate(payload.sellerId(), payload.reservationDate()))
            throw new BadRequestException("Seller has already a reservation for this date");
        if (this.reservationDAO.existsByCarIdAndReservationDate(payload.carId(), payload.reservationDate()))
            throw new BadRequestException("Car has already a reservation for this date");
        Reservation newReservation = new Reservation(payload.reservationDate(), this.carService.findById(payload.carId()), this.userService.findById(clientId), this.userService.findById(payload.sellerId()));
        return this.reservationDAO.save(newReservation);
    }

    public Page<Reservation> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return this.reservationDAO.findAll(pageable);
    }

    public List<Reservation> findAllByClientId(long clientId) {
        return this.reservationDAO.findAllByClientId(clientId);
    }

    public Reservation findById(long id) {
        return this.reservationDAO.findById(id).orElseThrow(() -> new NotFoundException("reservation with id " + id + " has not been found"));
    }

    public Reservation findByIdAndUpdate(long id, UpdateReservationDTO payload) {
        Reservation found = this.findById(id);
        if (this.reservationDAO.existsBySellerIdAndReservationDate(found.getSeller().getId(), payload.reservationDate()))
            throw new BadRequestException("Seller has already a reservation for this date");
        if (this.reservationDAO.existsByCarIdAndReservationDate(found.getCar().getId(), payload.reservationDate()))
            throw new BadRequestException("Car has already a reservation for this date");
        found.setReservationDate(payload.reservationDate());
        return this.reservationDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Reservation found = this.findById(id);
        this.reservationDAO.delete(found);
    }
}
