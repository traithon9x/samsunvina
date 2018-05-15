package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.Township;
import com.samsun.samsunvina.repositories.TownshipRepository;
import com.samsun.samsunvina.services.interfaces.TownshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TownshipServiceImpl implements TownshipService {

    public final TownshipRepository townshipRepository;

    @Autowired
    public TownshipServiceImpl(TownshipRepository townshipRepository) {
        this.townshipRepository = townshipRepository;
    }

    @Override
    public List<Township> getAllTownshipsByCityId(Integer cityId) {
        try {
            List<Township> townships = townshipRepository.findAllByCityId(cityId);
            return townships;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Township> getTownshipByTownshipId(Integer townshipId) {
        return townshipRepository.findById(townshipId);
    }
}
