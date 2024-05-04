package Fabio.Gilardi.CapstoneBE.entities;

import Fabio.Gilardi.CapstoneBE.enums.AccessoryName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Accessory {

    //    ATTRIBUTES
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Enumerated(EnumType.STRING)
    private AccessoryName accessoryName;

    @ManyToMany(mappedBy = "accessoryList")
    @JsonIgnore
    private List<Car> carList;

    //    CONSTRUCTORS
    public Accessory(AccessoryName accessoryName) {
        this.accessoryName = accessoryName;
    }
}
