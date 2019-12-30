package db;

import utils.DBUtils;
import utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VisitorDB {
    public  static void visit(HttpServletRequest request) throws SQLException {

        Connection conn = DBConnection.getConnection();
        String remoteAddr = request.getRemoteAddr();//得到来访者的IP地址
        String localAddr = request.getLocalAddr();		//获取WEB服务器的IP地址
        String remoteHost = request.getRemoteHost();    //获取主机名
        String time = DateUtils.getFormatDate(new Date());

        String sql ="insert into t_visitor values(null,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, remoteAddr);
            ps.setString(2, time);
            ps.setString(3, localAddr);
            ps.setString(4, remoteHost);
            ps.executeUpdate();
            DBUtils.close(conn,ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击
     * @return
     */
    public static int totalVisit() throws SQLException {
        Connection conn = DBConnection.getConnection();
        int result = 0;
        String sql ="select count(id) from t_visitor";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }
            DBUtils.close(conn,ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 访问电脑数
     * @return
     */
    public static int totalMember() throws SQLException {
        Connection conn = DBConnection.getConnection();
        int result = 0;
        String sql ="SELECT COUNT(DISTINCT(ip)) FROM t_visitor";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }
            DBUtils.close(conn,ps,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
