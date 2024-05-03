package Fabio.Gilardi.CapstoneBE.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    //    ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    private String title, description;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //    CONSTRUCTORS
    public Review(String title, String description, int rating, User user) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.user = user;
    }
}
