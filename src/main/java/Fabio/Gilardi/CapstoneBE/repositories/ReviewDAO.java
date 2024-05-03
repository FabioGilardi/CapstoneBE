package Fabio.Gilardi.CapstoneBE.repositories;

import Fabio.Gilardi.CapstoneBE.entities.Review;
import Fabio.Gilardi.CapstoneBE.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
}
