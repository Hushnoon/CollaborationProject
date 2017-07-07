package com.ali.onlinecollaborationbackend.initializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ali.onlinecollaborationbackend.CORSFilter;
import com.ali.onlinecollaborationbackend.config.HibernateConfig;
import com.ali.onlinecollaborationbackend.config.MvcConfig;
import com.ali.onlinecollaborationbackend.config.WebSocketConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * @Override public void onStartup(ServletContext servletContext) throws
	 * ServletException { // TODO Auto-generated method stub
	 * super.onStartup(servletContext); AnnotationConfigWebApplicationContext
	 * ctx = new AnnotationConfigWebApplicationContext();
	 * ctx.register(AppConfig.class); ctx.setServletContext(servletContext);
	 * ctx.refresh();
	 * 
	 * }
	 */

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// give all application related config class name
		return new Class[] { HibernateConfig.class, WebSocketConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { MvcConfig.class };
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new CORSFilter() };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		super.customizeRegistration(registration);
		System.out.println("custome registration");
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("10000000");
		factory.setMaxRequestSize("10000000");
		MultipartConfigElement multipartConfigElement = factory.createMultipartConfig();
		/*
		 * return factory.createMultipartConfig(); MultipartConfigElement
		 * multipartConfigElement = new MultipartConfigElement(
		 * "E:/DTJava/Training/ImagesOfCollaborationProject",10000000,10000000,
		 * 500);
		 */
		registration.setMultipartConfig(multipartConfigElement);
	}
}