
package ru.holyav.springapp.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = "ru.holyav.springapp")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class MyConfig {
    public MyConfig() {
    }


    @Autowired
    private Environment env;

    @Bean
    public JdbcTemplate getJdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        dataSource.setDriverClass(env.getProperty("database.driver"));
        dataSource.setJdbcUrl(env.getProperty("DATABASE_URL"));
        dataSource.setUser(env.getProperty("DATABASE_USERNAME"));
        dataSource.setPassword(env.getProperty("DATABASE_PASSWORD"));
        dataSource.setMinPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("database.minPoolSize"))));
        dataSource.setMaxPoolSize(Integer.parseInt(Objects.requireNonNull(env.getProperty("database.maxPoolSize"))));
        dataSource.setMaxIdleTime(Integer.parseInt(Objects.requireNonNull(env.getProperty("database.maxIdleTime"))));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws PropertyVetoException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(
                new String[]{"ru.holyav.springapp"});
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws PropertyVetoException {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;

    }


}



