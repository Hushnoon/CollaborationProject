package com.ali.onlinecollaborationbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@PostMapping("/fileupload")
	public ResponseEntity<Void> upload(MultipartFile f)
	{
		System.out.println(f.getOriginalFilename());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
