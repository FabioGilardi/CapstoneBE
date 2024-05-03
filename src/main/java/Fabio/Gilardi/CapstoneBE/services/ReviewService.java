package Fabio.Gilardi.CapstoneBE.services;

import Fabio.Gilardi.CapstoneBE.entities.Review;
import Fabio.Gilardi.CapstoneBE.exceptions.BadRequestException;
import Fabio.Gilardi.CapstoneBE.payloads.NewReviewDTO;
import Fabio.Gilardi.CapstoneBE.repositories.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewDAO reviewDAO;

    @Autowired
    UserService userService;

    public Review save(long id, NewReviewDTO payload) {
        Review newReview = new Review(payload.title(), payload.description(), payload.rating(), this.userService.findById(id));
        return this.reviewDAO.save(newReview);
    }

    public Page<Review> findAll(int number, int size, String sortBy) {
        Pageable pageable = PageRequest.of(number, size, Sort.by(sortBy));
        return this.reviewDAO.findAll(pageable);
    }

    public List<Review> findAllByUserId(long userId) {
        return this.reviewDAO.findByUserId(userId);
    }

    public Review findById(long id) {
        return this.reviewDAO.findById(id).orElseThrow(() -> new BadRequestException("review with id " + id + " has not been found"));
    }

    public Review findByIdAndUpdate(long id, NewReviewDTO payload) {
        Review found = this.findById(id);
        found.setTitle(payload.title());
        found.setDescription(payload.description());
        found.setRating(payload.rating());
        return this.reviewDAO.save(found);
    }

    public void findByIdAndDelete(long id) {
        Review found = this.findById(id);
        this.reviewDAO.delete(found);
    }
}
