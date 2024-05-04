package Fabio.Gilardi.CapstoneBE.entities;

import Fabio.Gilardi.CapstoneBE.enums.car.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {


    //    ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String picture, model;
    private int price, kilometers, power, displacements, fuelConsumption;
    private LocalDate registrationDate;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Fuel fuel;
    @Enumerated(EnumType.STRING)
    private EmissionClass emissionClass;
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;
    @Enumerated(EnumType.STRING)
    private DoorNumber doorNumber;
    @Enumerated(EnumType.STRING)
    private Color color;

    @ManyToMany
    @JoinTable(
            name = "car_accessory",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory_id"))
    private List<Accessory> accessoryList;

    //    CONSTRUCTORS
    public Car(String picture, String model, int price, int kilometers, int power, int displacements, int fuelConsumption, LocalDate registrationDate, Brand brand, Status status, Fuel fuel, EmissionClass emissionClass, TransmissionType transmissionType, DoorNumber doorNumber, Color color, List<Accessory> accessoryList) {
        this.picture = picture;
        this.model = model;
        this.price = price;
        this.kilometers = kilometers;
        this.power = power;
        this.displacements = displacements;
        this.fuelConsumption = fuelConsumption;
        this.registrationDate = registrationDate;
        this.brand = brand;
        this.status = status;
        this.fuel = fuel;
        this.emissionClass = emissionClass;
        this.transmissionType = transmissionType;
        this.doorNumber = doorNumber;
        this.color = color;
        this.accessoryList = accessoryList;
    }
}
