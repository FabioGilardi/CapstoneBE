package Fabio.Gilardi.CapstoneBE.repositories;

import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndEmail(String username, String email);

    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);
}
