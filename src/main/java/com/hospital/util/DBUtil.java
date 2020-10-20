package com.hospital.util;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class DBUtil {
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_DRIVER_CLASS = "driver.class.name";

    private static ComboPooledDataSource dataSource;

    static {
        try {
            URL resource = DBUtil.class.getClassLoader().getResource("database.properties");
            Properties properties = new Properties();
            properties.load(new FileInputStream(resource.toURI().getPath()));
            dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(properties.getProperty(DB_DRIVER_CLASS));
            dataSource.setJdbcUrl(properties.getProperty(DB_URL));
            dataSource.setUser(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));
            dataSource.setMinPoolSize(100);
            dataSource.setMaxPoolSize(1000);
            dataSource.setAcquireIncrement(5);
        } catch (IOException | PropertyVetoException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}
