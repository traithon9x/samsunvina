package com.samsun.samsunvina.controllers;

import com.samsun.samsunvina.entities.Township;
import com.samsun.samsunvina.services.interfaces.TownshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/townships")
public class TownshipController {

    private final TownshipService townshipService;

    @Autowired
    public TownshipController(TownshipService townshipService) {
        this.townshipService = townshipService;
    }

    @CrossOrigin
    @GetMapping()
    @ResponseBody
    public List<Township> getAllTownshipsByCityId(@RequestParam Integer cityId) {
        return townshipService.getAllTownshipsByCityId(cityId);
    }

}
