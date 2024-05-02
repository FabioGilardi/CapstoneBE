package Fabio.Gilardi.CapstoneBE.services;

import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.exceptions.UnauthorizedException;
import Fabio.Gilardi.CapstoneBE.payloads.NewUserDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UserLoginDTO;
import Fabio.Gilardi.CapstoneBE.repositories.UserDAO;
import Fabio.Gilardi.CapstoneBE.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserDAO userDAO;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTTools jwtTools;

    public User register(NewUserDTO payload) {
        if (this.userDAO.existsByUsernameAndEmail(payload.username(), payload.email()))
            throw new BadRequestException("Username and email are already taken");
        if (this.userDAO.existsByUsername(payload.username()))
            throw new BadRequestException("Username is already taken");
        if (this.userDAO.existsByEmail(payload.email())) throw new BadRequestException("Email is already taken");
        User newUser = new User(payload.username(), payload.email(), encoder.encode(payload.password()), payload.name(), payload.surname(), payload.birthDate());
        if (newUser.getAge() < 18) throw new BadRequestException("You can't register if you are a minor");
        return this.userDAO.save(newUser);
    }

    public String login(UserLoginDTO payload) {
        User found = this.userService.findByEmail(payload.email());
        if (!encoder.matches(payload.password(), found.getPassword()))
            throw new UnauthorizedException("Password is wrong");
        return jwtTools.createToken(found);
    }
}
