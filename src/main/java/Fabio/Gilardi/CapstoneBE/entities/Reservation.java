package Fabio.Gilardi.CapstoneBE.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    //    ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private LocalDateTime reservationDate;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    //    CONSTRUCTORS
    public Reservation(LocalDateTime reservationDate, Car car, User client, User seller) {
        this.reservationDate = reservationDate;
        this.car = car;
        this.client = client;
        this.seller = seller;
    }
}
