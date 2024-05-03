package Fabio.Gilardi.CapstoneBE.entities;

import Fabio.Gilardi.CapstoneBE.enums.AccessoryName;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private AccessoryName accessoryName;

    //    CONSTRUCTORS
    public Accessory(AccessoryName accessoryName) {
        this.accessoryName = accessoryName;
    }
}
