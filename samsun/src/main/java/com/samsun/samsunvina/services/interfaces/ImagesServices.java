package com.samsun.samsunvina.services.interfaces;

import com.samsun.samsunvina.entities.Image;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

import java.util.List;

public interface ImagesServices {
    String save(Integer id,List<MultipartFile> ct,MultipartFile cel,MultipartFile pano);

    List<Image> findOneByPatient(Integer patient_id);
    void deleteById(int id);
    Image changeImageName(int id,String name);
    File getFileByImage(Image image);
}
