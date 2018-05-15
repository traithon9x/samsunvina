package com.samsun.samsunvina.validators;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class UploadFileValidator {
    public static final String regexTypeImages = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP)$";



    public Boolean UploadFileValidator(List<MultipartFile> ct,
                                       MultipartFile pano ,
                                       MultipartFile cel){
        Boolean isreal = true;
        for (MultipartFile uploadedFile : ct) {
            String namefile = uploadedFile.getOriginalFilename();
            if(!namefile.trim().matches(regexTypeImages)){
                isreal=false;

            }
        }
        if(!pano.getOriginalFilename().trim().matches(regexTypeImages) ){
            isreal =false;
        }
        if(cel.getOriginalFilename().trim().matches(regexTypeImages) !=true){
            isreal =false;
        }
        return isreal;
    }

    public static void main(String[] args) {
        Boolean b=false;
        System.out.println(b);
        String a = "31947881_1627741857340799_3615904959020662784_n.jpg";
        System.out.println(a.trim().matches(regexTypeImages));
        if(!a.trim().matches(regexTypeImages)){
            System.out.println("abc");
        }
    }

}
