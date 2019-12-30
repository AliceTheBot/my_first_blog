package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    /*
     * 关闭数据库连接
     */
    public static void close(Connection con, Statement st) throws SQLException {
        if(con!=null)
            con.close();
        if(st!=null)
            st.close();
    }
    public static void close(Connection con, Statement st, ResultSet rs) throws SQLException {
        if(con!=null)
            con.close();
        if(st!=null)
            st.close();
        if(rs!=null)
            rs.close();
    }
}
