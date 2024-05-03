package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.entities.Review;
import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.NewReviewDTO;
import Fabio.Gilardi.CapstoneBE.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Review> findAll(@RequestParam(defaultValue = "0") int number,
                                @RequestParam(defaultValue = "30") int size,
                                @RequestParam(defaultValue = "id") String sortBy) {
        return this.reviewService.findAll(number, size, sortBy);
    }

    @GetMapping("/me")
    public List<Review> findAllByUserId(@AuthenticationPrincipal User currentUser) {
        return this.reviewService.findAllByUserId(currentUser.getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review save(@AuthenticationPrincipal User currentUser,
                       @RequestBody @Validated NewReviewDTO payload,
                       BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.reviewService.save(currentUser.getId(), payload);
    }

    @PutMapping("/{id}")
    public Review findByIdAndUpdate(@PathVariable long id,
                                    @RequestBody @Validated NewReviewDTO payload,
                                    BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        return this.reviewService.findByIdAndUpdate(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable long id) {
        this.reviewService.findByIdAndDelete(id);
    }
}
