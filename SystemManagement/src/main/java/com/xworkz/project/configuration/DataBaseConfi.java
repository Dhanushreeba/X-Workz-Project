
package com.xworkz.project.configuration;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Slf4j
public class DataBaseConfi {

    private static final Logger log = LoggerFactory.getLogger(DataBaseConfi.class);

    public DataBaseConfi(){
        DataBaseConfi.log.info("Created DataBaseConfi");
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

        log.info("Registred DataSource dataBaseConfi");
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(DataSource dataSource){

        log.info("created LocalContainerEntityManagerFactoryBean in dataBaseConfi");
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
