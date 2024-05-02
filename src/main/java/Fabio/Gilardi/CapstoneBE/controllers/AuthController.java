package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.LoginResponseDTO;
import Fabio.Gilardi.CapstoneBE.payloads.NewUserDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UserLoginDTO;
import Fabio.Gilardi.CapstoneBE.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Validated NewUserDTO payload,
                         BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.authService.register(payload);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Validated UserLoginDTO payload,
                                  BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return new LoginResponseDTO(this.authService.login(payload));
    }

}
