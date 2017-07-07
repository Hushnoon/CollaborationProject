package com.ali.onlinecollaborationbackend.config;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

@ApplicationPath("/rest")
public class ImageUploadApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		System.out.println("f.getOriginalFilename()1");
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(UploadImage.class);
		//s.add(MultiPartFeature.class);
		return s;

	}

}
