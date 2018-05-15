package com.samsun.samsunvina.services;

import com.samsun.samsunvina.entities.Contact;
import com.samsun.samsunvina.enumerations.Status;
import com.samsun.samsunvina.models.ContactModel;
import com.samsun.samsunvina.repositories.ContactRepository;
import com.samsun.samsunvina.services.interfaces.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{
    @Autowired
    ContactRepository contactRepository;

    @Override
    public String SaveContact(ContactModel contactModel){
        Contact contact = new Contact();
        contact.setName(contactModel.getName());
        contact.setMail(contactModel.getMail());
        contact.setPhone(contactModel.getPhone());
        contact.setSubject(contactModel.getSubject());
        contact.setWrite_comment(contactModel.getWrite_comment());
        contact.setStatus(Status.ACTIVE);
        contactRepository.save(contact);

        return "SUCCESS";
    }



}
