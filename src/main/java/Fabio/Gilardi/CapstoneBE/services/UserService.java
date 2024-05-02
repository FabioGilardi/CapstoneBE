package Fabio.Gilardi.CapstoneBE.services;

import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.exceptions.NotFoundException;
import Fabio.Gilardi.CapstoneBE.payloads.UpdatePasswordDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdateUserDTO;
import Fabio.Gilardi.CapstoneBE.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder encoder;

    public Page<User> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return this.userDAO.findAll(pageable);
    }

    public User findById(long id) {
        return this.userDAO.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " has not been found"));
    }

    public User findByIdAndUpdate(long id, UpdateUserDTO payload) {
        User found = this.findById(id);
        if (found.getEmail().equals(payload.email()) && found.getUsername().equals(payload.username())) {
            found.setName(payload.name());
            found.setSurname(payload.surname());
            found.setDefaultAvatar();
        }
        if (found.getEmail().equals(payload.email()) && !found.getUsername().equals(payload.username())) {
            if (this.userDAO.existsByUsername(payload.username()))
                throw new BadRequestException("Username is already taken");
            found.setName(payload.name());
            found.setSurname(payload.surname());
            found.setUsername(payload.username());
            found.setDefaultAvatar();
        }
        if (found.getUsername().equals(payload.username()) && !found.getEmail().equals(payload.email())) {
            if (this.userDAO.existsByEmail(payload.email())) throw new BadRequestException("Email is already taken");
            found.setName(payload.name());
            found.setSurname(payload.surname());
            found.setEmail(payload.email());
            found.setDefaultAvatar();
        }
        if (!found.getUsername().equals(payload.username()) && !found.getEmail().equals(payload.email())) {
            if (this.userDAO.existsByUsernameAndEmail(payload.username(), payload.email()))
                throw new BadRequestException("Username and email are already taken");
            if (this.userDAO.existsByUsername(payload.username()))
                throw new BadRequestException("Username is already taken");
            if (this.userDAO.existsByEmail(payload.email())) throw new BadRequestException("Email is already taken");
            found.setName(payload.name());
            found.setSurname(payload.surname());
            found.setEmail(payload.email());
            found.setUsername(payload.username());
            found.setDefaultAvatar();
        }
        return this.userDAO.save(found);
    }

    public String findByIdAndUpdatePassword(long id, UpdatePasswordDTO payload) {
        User found = this.findById(id);
        if (encoder.matches(payload.newPassword(), found.getPassword()))
            throw new BadRequestException("You must change the password");
        if (!encoder.matches(payload.oldPassword(), found.getPassword()))
            throw new BadRequestException("The actual password is wrong");
        found.setPassword(encoder.encode(payload.newPassword()));
        this.userDAO.save(found);
        return "Password has been changed correctly";
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        this.userDAO.delete(found);
    }

    public User findByEmail(String email) {
        return this.userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " has not been found"));
    }
}
