package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.Xray;
import org.springframework.stereotype.Service;

public interface XrayServices {
    Iterable<Xray> findAll();
}
