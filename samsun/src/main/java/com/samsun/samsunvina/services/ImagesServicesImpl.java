package com.samsun.samsunvina.services;

import com.samsun.samsunvina.controllers.UploadController;
import com.samsun.samsunvina.entities.Image;
import com.samsun.samsunvina.entities.Patient;
import com.samsun.samsunvina.enumerations.Status;
import com.samsun.samsunvina.repositories.ImagesRepository;
import com.samsun.samsunvina.repositories.PatientRepository;
import com.samsun.samsunvina.services.interfaces.ImagesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImagesServicesImpl implements ImagesServices {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    ImagesRepository imagesRepository;
    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    public String save(Integer id, List<MultipartFile> ct, MultipartFile cel, MultipartFile pano) {
        Patient patient = patientRepository.findOne(id);
        try {
            for (MultipartFile uploadedFile : ct) {
                if (!uploadedFile.isEmpty()) {
                    File file = new File(uploadingdir + uploadedFile.getOriginalFilename());

                    uploadedFile.transferTo(file);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        for (MultipartFile uploadedFile : ct) {
            Image image = new Image();

            if (!uploadedFile.isEmpty()) {
                image.setName(uploadedFile.getOriginalFilename());
                image.setType("CT");
                image.setPatient(patient);
                image.setStatus(Status.ACTIVE);
                imagesRepository.save(image);
            }

        }
        //upload cel or pano
        try {
            if (!pano.isEmpty()) {
            File newpano = new File(uploadingdir, pano.getOriginalFilename());
            pano.transferTo(newpano);
            Image imagepano = new Image();
            imagepano.setPatient(patient);
            imagepano.setType("Panorama");
            imagepano.setName(pano.getOriginalFilename());
            imagepano.setStatus(Status.ACTIVE);
            imagesRepository.save(imagepano);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            if (!cel.isEmpty()) {

                File newcel = new File(uploadingdir, cel.getOriginalFilename());
            cel.transferTo(newcel);
            Image imagecel = new Image();
            imagecel.setName(cel.getOriginalFilename());
            imagecel.setPatient(patient);
            imagecel.setStatus(Status.ACTIVE);
            imagecel.setType("Celphalo");
            imagesRepository.save(imagecel);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        patient.setStatus(Status.FINISHED);
        patientRepository.save(patient);
        return null;
    }


    public List<Image> findOneByPatient(Integer patient_id) {
        return imagesRepository.findOneByPatient(patient_id);
    }

    @Override
    public void deleteById(int id) {
        // TODO Auto-generated method stub
        Image image = this.imagesRepository.findOne(id);
        //delete object
        this.imagesRepository.delete(image);
        //delete file content
        File file = new File(uploadingdir + image.getName());
        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }
    }

    @Override
    public Image changeImageName(int id, String name) {
        Image img = imagesRepository.findOne(id);
        if (img != null && isValidFileName(img, name)) {
            File file = new File(uploadingdir + img.getName());
            file.renameTo(new File(uploadingdir + name + "." + img.getFileExtension()));
            img.setName(name + "." + img.getFileExtension());
            return imagesRepository.save(img);
        }
        return null;
    }

    private boolean isValidFileName(Image image, String name) {
        String newName = name + "." + image.getFileExtension();
        File folder = new File(UploadController.uploadingdir);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; ++i) {
            if (newName.equals(listOfFiles[i].getName())) return false;
        }
        return true;
    }

    @Override
    public File getFileByImage(Image image) {
        // TODO Auto-generated method stub
        return new File(uploadingdir + image.getName());
    }

}
