package daoImpl;

import dao.UserDao;
import db.DBConnection;
import model.User;
import utils.DBUtils;
import utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private Connection con;

    public static UserDao getInstance(){
        return new UserDaoImpl();
    }
    public User register(String username, String password) {
        String sql0="SELECT * FROM t_user WHERE user_name=?";
        String sql="INSERT INTO t_user(user_name,user_password,power) VALUES(?,?,2)";
        try {
            con= DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql0);
            statement.setString(1,username);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
                return null;

            statement=con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.executeUpdate();
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return login(username,password);
    }

    public User login(String username, String password) {
        String sql="SELECT * FROM t_user WHERE user_name=? AND user_password=?";
        User user=new User();
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                user.setUser_id(rs.getInt("user_id"));
                user.setUser_name((rs.getString("user_name")));
                user.setUser_password(rs.getString("user_password"));
                user.setUser_power(rs.getInt("power"));
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(StringUtils.isBlank(user.getUser_name()))
            return null;
        return user;
    }
}
