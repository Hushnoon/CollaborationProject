package com.ali.onlinecollaborationbackend.initializer;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ali.onlinecollaborationbackend.CORSFilter;
import com.ali.onlinecollaborationbackend.config.HibernateConfig;
import com.ali.onlinecollaborationbackend.config.MvcConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //give all application related config class name
    	return new Class[] {HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    @Override
    protected Filter[] getServletFilters() {
    	return new Filter[]{new CORSFilter()};
    }
}