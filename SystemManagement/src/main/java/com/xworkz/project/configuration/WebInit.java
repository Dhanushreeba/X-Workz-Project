package com.xworkz.project.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Slf4j
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer implements WebMvcConfigurer {

    public WebInit(){
        System.out.println("Created constr for webInt");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.info("running getRootConfigClasses");
        return new Class[0];
    }


    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.info("running getServletConfigClasses");
        return new Class[ ]{SpringConfi.class};
    }

    @Override
    protected String[] getServletMappings() {
        logger.info("running getServletMappings");
        return new String[ ]{"/"};
    }

}
