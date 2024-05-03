package Fabio.Gilardi.CapstoneBE.controllers;

import Fabio.Gilardi.CapstoneBE.services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accessories")
public class AccessoryController {

    @Autowired
    private AccessoryService accessoryService;

    @PostMapping("/populate")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void populateDB() {
        this.accessoryService.populateDB();
    }
}
