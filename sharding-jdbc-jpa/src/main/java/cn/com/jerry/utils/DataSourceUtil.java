package cn.com.jerry.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

public class DataSourceUtil {

    public static DataSource dataSource(String dataSourceName) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://192.168.136.132:3306/%s", dataSourceName));
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

}
