package Fabio.Gilardi.CapstoneBE.repositories;

import Fabio.Gilardi.CapstoneBE.entities.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryDAO extends JpaRepository<Accessory, Long> {
}
