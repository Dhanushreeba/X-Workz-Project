package com.xworkz.project.configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

    public WebInit(){
        System.out.println("Created constr for webInt");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("running getRootConfigClasses");
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        System.out.println("running getServletConfigClasses");
        return new Class[ ]{SpringConfi.class};
    }

    @Override
    protected String[] getServletMappings() {
        System.out.println("running getServletMappings");
        return new String[ ]{"/"};
    }
}
