package Fabio.Gilardi.CapstoneBE.services;

import Fabio.Gilardi.CapstoneBE.entities.Car;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.NewCarDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdateCarDTO;
import Fabio.Gilardi.CapstoneBE.repositories.CarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    CarDAO carDAO;

    public Car save(NewCarDTO payload) {
        Car newCar = new Car(payload.picture(), payload.model(), payload.price(), payload.kilometers(), payload.power(), payload.displacements(), payload.fuelConsumption(), payload.registrationDate(), payload.brand(), payload.status(), payload.fuel(), payload.emissionClass(), payload.transmissionType(), payload.doorNumber(), payload.color());
        return this.carDAO.save(newCar);
    }

    public Page<Car> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return this.carDAO.findAll(pageable);
    }

    public Car findById(long id) {
        return this.carDAO.findById(id).orElseThrow(() -> new BadRequestException("car with id " + id + " has not been found"));
    }

    public Car findByIdAndUpdate(long id, UpdateCarDTO payload) {
        Car found = this.findById(id);
        found.setPicture(payload.picture());
        found.setPrice(payload.price());
        found.setColor(payload.color());
        return this.carDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Car found = this.findById(id);
        this.carDAO.delete(found);
    }

}
