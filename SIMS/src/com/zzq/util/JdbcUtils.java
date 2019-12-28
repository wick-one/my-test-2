package com.zzq.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    //数据库连接池对象
    private static DataSource dataSource;

    //初始化连接池
    static {
        Properties properties = new Properties();
        try {
            //加载配置Druid配置文件
            properties.load(JdbcUtils.class.getClassLoader().getResourceAsStream("com/zzq/util/druid.properties"));
            //通过工厂类来获取连接池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //返回连接池对象
    public static DataSource getDataSource() {
        return dataSource;
    }

    //返回连接器
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
