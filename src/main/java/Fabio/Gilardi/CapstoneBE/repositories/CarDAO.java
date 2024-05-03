package Fabio.Gilardi.CapstoneBE.repositories;

import Fabio.Gilardi.CapstoneBE.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDAO extends JpaRepository<Car, Long> {
}
