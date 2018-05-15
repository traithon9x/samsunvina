package com.samsun.samsunvina;

import com.samsun.samsunvina.controllers.UploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class SamsunvinaApplication {

	public static void main(String[] args) {
		new File(UploadController.uploadingdir).mkdirs();
		SpringApplication.run(SamsunvinaApplication.class, args);
	}
}
