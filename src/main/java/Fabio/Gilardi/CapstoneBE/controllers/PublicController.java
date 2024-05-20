package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.entities.Car;
import Fabio.Gilardi.CapstoneBE.entities.Review;
import Fabio.Gilardi.CapstoneBE.entities.User;
import Fabio.Gilardi.CapstoneBE.services.CarService;
import Fabio.Gilardi.CapstoneBE.services.ReviewService;
import Fabio.Gilardi.CapstoneBE.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    CarService carService;

    @Autowired
    UserService userService;

    @GetMapping("/reviews")
    public Page<Review> findAllReviews(@RequestParam(defaultValue = "0") int number,
                                       @RequestParam(defaultValue = "30") int size,
                                       @RequestParam(defaultValue = "id") String sortBy) {
        return this.reviewService.findAll(number, size, sortBy);
    }

    @GetMapping("/cars")
    public Page<Car> findAllCars(@RequestParam(defaultValue = "0") int number,
                                 @RequestParam(defaultValue = "50") int size,
                                 @RequestParam(defaultValue = "id") String sortBy) {
        return this.carService.findAll(number, size, sortBy);
    }

    @GetMapping("/cars/{id}")
    public Car findById(@PathVariable long id) {
        return this.carService.findById(id);
    }

    @GetMapping("/sellers")
    public List<User> findSellers() {
        return this.userService.findSellers();
    }
}
