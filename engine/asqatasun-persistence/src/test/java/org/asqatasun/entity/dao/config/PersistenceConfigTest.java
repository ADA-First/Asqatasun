/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.entity.dao.config;

import org.asqatasun.persistence.config.PersistenceCommonConfig;
import org.asqatasun.persistence.config.PersistenceConfig;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Persistence configuration.
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({"${packages.to.scan}"})
@PropertySource(value = {
        "classpath:default-hibernate.properties",
        "classpath:flyway.properties",
        "classpath:datasource.properties"},
        ignoreResourceNotFound = true)
public class PersistenceConfigTest extends PersistenceCommonConfig {

    @Value("${jdbc.user:sa}")
    private String username;
    @Value("${jdbc.password:}")
    private String password;
    @Value("${jdbc.url:jdbc:hsqldb:mem:mytestdb}")
    private String url;
    @Value("${useComboPool}")
    private boolean useComboPool;
    @Value("${packages.to.scan}")
    private String packagesToScan;

    @Bean
    static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "dataSource")
    DataSource dataSource() {
        System.out.println(packagesToScan);
        if (useComboPool) {
            try {
                return setUpComboPooledDataSource(url, username, password);
            } catch (PropertyVetoException ex) {
                return setUpBasicDataSource(url, username, password);
            }
        }
        return setUpBasicDataSource(url, username, password);
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println(packagesToScan);
        System.out.println(packagesToScan.split(","));
        return entityManagerFactory(dataSource(), url, packagesToScan.split(","));
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

}