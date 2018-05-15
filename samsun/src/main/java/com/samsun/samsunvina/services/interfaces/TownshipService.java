package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.Township;

import java.util.List;
import java.util.Optional;

public interface TownshipService {
    List<Township> getAllTownshipsByCityId(Integer city);

    Optional<Township> getTownshipByTownshipId(Integer townshipId);
}
