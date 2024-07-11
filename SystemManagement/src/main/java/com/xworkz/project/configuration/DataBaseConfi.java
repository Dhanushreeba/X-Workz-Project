package com.xworkz.project.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

//DataBaseConfi is to set the connection for database
//@Configuration
@Configuration
public class DataBaseConfi {

    public DataBaseConfi(){
        System.out.println("Created DataBaseConfi");
    }

    //@value is to
    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;

    //@Bean is to
    @Bean
    public DataSource dataSource(){

        System.out.println("Registred DataSource dataBaseConfi");
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(DataSource dataSource){

        System.out.println("created LocalContainerEntityManagerFactoryBean in dataBaseConfi");
        LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(dataSource);
        JpaVendorAdapter jpaVendorAdapter=new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        // this to scan packages of dto class that includes fully qualified name with dto class
        bean.setPackagesToScan("com.xworkz.project.dto");

        Properties properties=new Properties();
        properties.put("hibernate.show_sql","true");
        bean.setJpaProperties(properties);
        return bean;

    }

}
