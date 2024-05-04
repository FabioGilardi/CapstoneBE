package Fabio.Gilardi.CapstoneBE.repositories;

import Fabio.Gilardi.CapstoneBE.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByClientId(long clientId);

    boolean existsByClientIdAndReservationDate(long clientId, LocalDateTime reservationDate);

    boolean existsBySellerIdAndReservationDate(long sellerId, LocalDateTime reservationDate);

    boolean existsByCarIdAndReservationDate(long carId, LocalDateTime reservationDate);
}
