package daoImpl;

import dao.CommentDao;
import db.DBConnection;
import model.Comment;
import utils.DBUtils;
import utils.DateUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private Connection con;

    public static CommentDao getInstance(){
        return new CommentDaoImpl();
    }

    public boolean deleteComment(int comment_id) {
        String sql="DELETE FROM t_comment WHERE id="+comment_id;
        int result=0;
        //article -> commend -1
        article_sub_comment(comment_id);
        try {
            con= DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            result=statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    /*
     * 文章的评论数减一
     */
    private void article_sub_comment(int comment_id){
        String sql="SELECT article_id FROM t_comment WHERE id="+comment_id;
        int article_id=-1;
        try {
            con= DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            //获取文章id
            while(rs.next()){
                article_id=rs.getInt(1);
            }

            //对文字评论数-1
            sql="UPDATE t_article SET comment=comment-1 WHERE id="+article_id;
            statement=con.prepareStatement(sql);
            statement.executeUpdate();

            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Comment addComment(Comment comment) {
        String sql="INSERT INTO t_comment VALUES(null,?,?,?,?,?,?)";
        int result=0;
        Comment c=null;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);

            statement.setInt(1,comment.getArticle_id());
            statement.setString(2,comment.getNickname());
            statement.setString(3,comment.getContent());
            statement.setString(4,comment.getTime());
            statement.setInt(5,comment.getStar());
            statement.setInt(6,comment.getDiss());

            result=statement.executeUpdate();

            //更新评论数
            sql="UPDATE t_article SET comment=comment+1 WHERE id="+comment.getArticle_id();
            statement=con.prepareStatement(sql);
            statement.executeUpdate();

            //获取添加的评论
            sql="SELECT * FROM t_comment WHERE content=? AND time=?";
            statement=con.prepareStatement(sql);
            statement.setString(1,comment.getContent());
            statement.setString(2,comment.getTime());
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                c=new Comment();
                c.setId(rs.getInt("id"));
                c.setNickname(rs.getString("nickname"));
                c.setTime(rs.getString("time"));
                c.setContent(rs.getString("content"));
            }

            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public List getComment(int article_id) {
        String sql="SELECT * FROM t_comment WHERE article_id="+article_id+" ORDER BY TIME DESC";
        List list=new ArrayList();
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Comment c=new Comment();
                c.setId(rs.getInt("id"));
                c.setArticle_id(rs.getInt("article_id"));

                if (rs.getString("nickname") == null)
                    c.setNickname("游客");
                else
                    c.setNickname(rs.getString("nickname"));

                c.setContent(rs.getString("content"));
                c.setTime(rs.getString("time"));
                c.setStar(rs.getInt("star"));
                c.setDiss(rs.getInt("diss"));

                list.add(c);
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()==0)
            return null;
        return list;
    }

    public int star_diss(int id, int star_or_diss) {
        int result=0;
        String sql=null;

        if(star_or_diss==Comment.STAR)
            sql="UPDATE t_comment SET star=star+1 WHERE id="+id;
        else if(star_or_diss==Comment.DISS)
            sql="UPDATE t_comment SET diss=diss+1 WHERE id="+id;

        return exeUpdateAndGetStarOrDissNum(id, star_or_diss, result, sql);
    }

    public int unStar_diss(int id, int star_or_diss) {
        int result=0;
        String sql=null;

        if(star_or_diss==Comment.STAR)
            sql="UPDATE t_comment SET star=star-1 WHERE id="+id;
        else if(star_or_diss==Comment.DISS)
            sql="UPDATE t_comment SET diss=diss-1 WHERE id="+id;

        return exeUpdateAndGetStarOrDissNum(id, star_or_diss, result, sql);
    }

    private int exeUpdateAndGetStarOrDissNum(int id, int star_or_diss, int result, String sql) {
        try {
            con= DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.executeUpdate();

            if(star_or_diss== Comment.STAR)
                sql="SELECT star FROM t_comment WHERE id="+id;
            else if(star_or_diss==Comment.DISS)
                sql="SELECT diss FROM t_comment WHERE id="+id;

            statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next())
                result=rs.getInt(1);

            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
