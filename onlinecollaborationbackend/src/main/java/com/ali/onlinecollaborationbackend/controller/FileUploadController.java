package com.ali.onlinecollaborationbackend.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@PostMapping("/fileupload")
	public ResponseEntity<Void> upload(@RequestParam("file") MultipartFile file) {
		System.out.println("File getting uploaded");
		String fileName = null;
		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				System.out.println("FileName:"+fileName);
				byte[] bytes = file.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(
						new FileOutputStream(new File("E:/DTJava/Training/ImagesOfCollaborationProject/" + fileName)));
				buffStream.write(bytes);
				buffStream.close();
				return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/ProfileImageUpload")
	public ResponseEntity<Void> uploadProfileImage(@RequestParam("file") MultipartFile file) {
		System.out.println("File getting uploaded");
		String fileName = null;
		if (!file.isEmpty()) {
			try {
				fileName = file.getOriginalFilename();
				System.out.println("FileName:"+fileName);
				byte[] bytes = file.getBytes();
				BufferedOutputStream buffStream = new BufferedOutputStream(
						new FileOutputStream(new File("E:/DTJava/Training/ImagesOfCollaborationProject/" + fileName)));
				buffStream.write(bytes);
				buffStream.close();
				return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
			}
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

	}
}
