package com.local.image.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.image.service.PrintWriterPicture;

@RestController
public class Controllers {


	@GetMapping( "/getimages")
	public static List<byte[]> getImages(String toll, String lane, String date, String hour, String type){
		List<byte[]> results = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime dateTime = LocalDateTime.parse(date + hour,formatter);
		PrintWriterPicture pwp = new PrintWriterPicture( toll, lane, dateTime, type );
		
		int size = pwp.listFiles().length;
		for (int i = 1; i<= size; i++) {
			results.add(pwp.encode(i).getBytes());
		}
		
		
		return  results;
		
	}
}
