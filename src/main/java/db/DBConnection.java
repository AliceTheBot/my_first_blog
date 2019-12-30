package db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

//阿里家的 Druid 连接池
public class DBConnection {
    private static DruidDataSource dataSource = null;

    /*
     * 初始化 dataSource 对象
     */
    static{
        //Class.getResource 加 ‘/’ 是web-INF/class目录,resources中的内容放在这儿
        InputStream in=DBConnection.class.getResourceAsStream("/db_server.properties");//获取输入流
        Properties properties=new Properties();
        try {
            properties.load(in);//从输入流中读取键值对
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);//使用db_server.properties初始化数据池对象
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * return DruidPooledConnection
     **/
    public static DruidPooledConnection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
