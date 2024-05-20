package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.PasswordResponseDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdatePasswordDTO;
import Fabio.Gilardi.CapstoneBE.payloads.UpdateUserDTO;
import Fabio.Gilardi.CapstoneBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    public User findMe(@AuthenticationPrincipal User currentUser) {
        return currentUser;
    }

    @PutMapping("/me")
    public User findMeAndUpdate(@AuthenticationPrincipal User currentUser,
                                @RequestBody @Validated UpdateUserDTO payload,
                                BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.userService.findByIdAndUpdate(currentUser.getId(), payload);
    }

    @PutMapping("/me/password")
    public PasswordResponseDTO findMeAndUpdatePassword(@AuthenticationPrincipal User currentUser,
                                                       @RequestBody @Validated UpdatePasswordDTO payload,
                                                       BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return new PasswordResponseDTO(this.userService.findByIdAndUpdatePassword(currentUser.getId(), payload));
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findMeAndDelete(@AuthenticationPrincipal User currentUser) {
        this.userService.findByIdAndDelete(currentUser.getId());
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> findAll(@RequestParam(defaultValue = "0") int number,
                              @RequestParam(defaultValue = "50") int size,
                              @RequestParam(defaultValue = "id") String sortBy) {
        return this.userService.findAll(number, size, sortBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findById(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable long id,
                                  @RequestBody @Validated UpdateUserDTO payload,
                                  BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.userService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.userService.findByIdAndDelete(id);
    }
}
