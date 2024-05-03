package Fabio.Gilardi.CapstoneBE.services;

import Fabio.Gilardi.CapstoneBE.entities.Accessory;
import Fabio.Gilardi.CapstoneBE.enums.AccessoryName;
import Fabio.Gilardi.CapstoneBE.exceptions.NotFoundException;
import Fabio.Gilardi.CapstoneBE.repositories.AccessoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccessoryService {

    @Autowired
    private AccessoryDAO accessoryDAO;

    public void populateDB() {
        List<Accessory> accessoryList = new ArrayList<>();
        accessoryList.add(new Accessory(AccessoryName.ALLOY_WHEELS));
        accessoryList.add(new Accessory(AccessoryName.SPORT_SETUP));
        accessoryList.add(new Accessory(AccessoryName.ELECTRIC_SEATS));
        accessoryList.add(new Accessory(AccessoryName.DUAL_ZONE_AIR_CONDITIONING));
        accessoryList.add(new Accessory(AccessoryName.PANORAMIC_ROOF));
        accessoryList.add(new Accessory(AccessoryName.ASSISTED_PARKING));
        accessoryList.add(new Accessory(AccessoryName.DASHCAM));
        accessoryList.add(new Accessory(AccessoryName.SENSORS));
        accessoryList.add(new Accessory(AccessoryName.CRUISE_CONTROL));
        accessoryList.add(new Accessory(AccessoryName.RUNFLAT_TYRES));
        accessoryList.add(new Accessory(AccessoryName.SPARE_WHEEL));
        accessoryList.add(new Accessory(AccessoryName.EMERGENCY_KIT));
        accessoryList.add(new Accessory(AccessoryName.FULL_LED_HEADLIGHTS));
        accessoryList.forEach(accessory -> this.accessoryDAO.save(accessory));
    }

    public Accessory findById(long id) {
        return this.accessoryDAO.findById(id).orElseThrow(() -> new NotFoundException("accessory with id " + id + " has not been found"));
    }
}
