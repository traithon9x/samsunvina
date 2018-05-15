package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.models.ContactModel;
import org.springframework.stereotype.Service;

public interface ContactService {
    String SaveContact(ContactModel contactModel);
}
