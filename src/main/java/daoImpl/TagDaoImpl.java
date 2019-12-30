package daoImpl;

import dao.TagDao;
import db.DBConnection;
import model.Tag;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagDaoImpl implements TagDao {

    private Connection con;

    public static TagDao getInstance(){
       return new TagDaoImpl();
    }

    public boolean addTag(int id, String tag) {
        String sql="INSERT INTO t_tag VALUES(?,?)";
        int result=0;
        try {
            con= DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,tag);
            result=statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    //TAG只能整体删除，而不能删除某一个特定文章的TAG
    //如果要删除特定文章的TAG，需要把OR改成AND
    public boolean deleteTag(int id, String tag) {
        String sql="DELETE FROM t_tag WHERE id=? OR tag=?";
        int result=0;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,id);
            statement.setString(2,tag);
            result=statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    public boolean updateTag(String old_tag, String new_tag) {
        String sql="UPDATE t_tag SET tag=? WHERE tag=?";
        int result=0;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,new_tag);
            statement.setString(2,old_tag);
            result=statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    public List getAllTag() {
        String sql="SELECT DISTINCT(tag) FROM t_tag";
        List<Tag> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs= statement.executeQuery();
            Tag tag=null;
            while(rs.next()){
                tag=new Tag();
                tag.setId(0);
                tag.setTag(rs.getString("tag"));
                list.add(tag);
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getTagByTag(String value) {
        String sql="SELECT * FROM t_tag WHERE tag = ?";//PreparedStatement 不能用来设置表名、列名
        List<Tag> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,value);
            ResultSet rs=statement.executeQuery();
            Tag tag=null;
            while(rs.next()){
                tag=new Tag();
                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));
                list.add(tag);
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List getTagById(int id) {
        String sql="SELECT * FROM t_tag WHERE id = ?";//PreparedStatement 不能用来设置表名、列名
        List<Tag> list=new ArrayList<>();
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            Tag tag=null;
            while(rs.next()){
                tag=new Tag();
                tag.setId(rs.getInt("id"));
                tag.setTag(rs.getString("tag"));
                list.add(tag);
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
