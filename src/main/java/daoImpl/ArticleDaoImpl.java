package daoImpl;

import dao.ArticleDao;
import db.DBConnection;
import model.Article;
import utils.ArticleUtils;
import utils.DBUtils;
import utils.DateUtils;
import utils.StringUtils;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ArticleDaoImpl implements ArticleDao {
    private Connection con;

    public static final ArticleDao getInstance(){
        return new ArticleDaoImpl();
    }

    public void addVisit(int article_id) {
        try {
            con= DBConnection.getConnection();
            String sql="UPDATE t_article SET visit = visit + 1 WHERE id=?";
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,article_id);
            statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Article getANearArticle(String time, int less_or_more) {
        Article article=null;
        String sql;

        //前一个（小于此时间并且时间最大）或者后一个（大于此时间并且时间最小）
        if(less_or_more == LESS){
            sql="SELECT * FROM t_article WHERE time < '"+time+"' ORDER BY TIME DESC LIMIT 1";//time 没加分号引起的惨案
        } else if(less_or_more == MORE){
            sql="SELECT * FROM t_article WHERE time > '"+time+"' ORDER BY TIME LIMIT 1";
        }else{
            return null;
        }

        try {
            con=DBConnection.getConnection();
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                article=new Article(rs.getInt("id"),rs.getString("title"),rs.getString("author"),rs.getString("sort"),
                        rs.getString("time"), rs.getInt("star"),rs.getInt("comment"),rs.getInt("visit"),rs.getString("content") );
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }

    public Map getColumnAndCount(String search_column) {
        Map map=new HashMap();
        //选择此列中的值及其count
        String sql="SELECT "+search_column+" ,count("+search_column+") AS counts FROM t_article GROUP BY "+search_column;
        try {
            con = DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                map.put(rs.getString(1),rs.getString(2));
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List getAllSort() {
        List<String> list=new ArrayList<String>();
        //选择所有的sort值
        String sql="SELECT DISTINCT sort FROM t_article";
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                list.add(rs.getString(1));
            }
            DBUtils.close(con,statement,rs);//不要老是忘记关连接啊
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Article addArticle(Article a) {
        String sql="INSERT INTO t_article VALUES(null,?,?,?,?,?,?,?,?)";
        try {
            con = DBConnection.getConnection();
            PreparedStatement statement= con.prepareStatement(sql);
            statement.setString(1,a.getTitle());
            statement.setString(2,a.getAuthor());
            statement.setString(3,a.getSort());
            statement.setString(4,a.getTime());
            statement.setInt(5,a.getStar());
            statement.setInt(6,a.getComment());
            statement.setInt(7,a.getVisit());
            statement.setString(8,a.getContent());
            statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //返回最新文章（就是刚刚更新的这个 but why?）
        return this.getLastArticle();
    }

    /*
     * 获取最新文章
     */
    private Article getLastArticle(){
        String time = DateUtils.getFormatDate(new Date());
        return getANearArticle(time,LESS);
    }

    public boolean deleteArticle(int id) {
        String sql="DELETE FROM t_article WHERE id=?";
        int result=0;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,id);
            result=statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(result>0)
            return true;
        return false;
    }

    public Article updateArticle(Article old_art) {
        String sql="UPDATE t_article SET title=?,author=?,sort=?,time=?,content=? WHERE id=?";
        Article new_art=null;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);

            statement.setString(1,old_art.getTitle());
            statement.setString(2,old_art.getAuthor());
            statement.setString(3,old_art.getSort());
            statement.setString(4,old_art.getTime());
            statement.setString(5,old_art.getContent());
            statement.setInt(6,old_art.getId());

            statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new_art=getArticle(old_art.getId());
        if(new_art==null)
            return null;

        return new_art;
    }

    public List getAllArticle() {
        List<Article> list = new <Article>ArrayList();
        String sql = "SELECT * FROM t_article";
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);

            //查询值并 po 到 list 里
            addItToList(statement,list);

            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //排序 article.compareTo();
        Collections.sort(list);
        return list;
    }

    public List getVisitRank() {
        List<Article> list=new ArrayList<>();
        String sql = "SELECT * FROM t_article WHERE visit >0 ORDER BY visit DESC LIMIT 0,6";
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);

            //查询值并 po 到 list 里
            addItToList(statement,list);

            ArticleUtils.cutTime(list);

            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Article> getArticleByColumn(String column, String value) {
        List<Article> list= new ArrayList();
        String sql="SELECT * FROM t_article WHERE "+column+" = ?";
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setString(1,value);

            //查询值并 po 到 list 里
            addItToList(statement,list);

            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //排序
        Collections.sort(list);
        return list;
    }

    /*
     * 获取具体某一文章
     */
    public Article getArticle(int id){
        Article article=null;
        String sql="SELECT * FROM t_article WHERE id = ?";
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                article=new Article(rs.getInt("id"),rs.getString("title"),rs.getString("author"),rs.getString("sort"),
                        rs.getString("time"), rs.getInt("star"),rs.getInt("comment"),rs.getInt("visit"),rs.getString("content") );
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(article==null)
            return null;
        ArticleUtils.cutTime(article);
        return article;
    }

    public int getCount(String search_key) {
        String sql=null;
        int count=0;

        //查询文章数还是类别数
        if(search_key.equals(SEARCH_ARTICLE))
            sql="SELECT COUNT(id) FROM t_article";
        else if(search_key.equals(SEARCH_SORT))
            sql="SELECT COUNT(DISTINCT(sort)) FROM t_article";

        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
            DBUtils.close(con,statement,rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int star_article(int id) {
        int result=0;
        String sql1="UPDATE t_article SET star=star+1 WHERE id=?";
        return star_diss_util(id, result, sql1);
    }

    @Override
    public int unStar_article(int id) {
        int result=0;
        String sql1="UPDATE t_article SET star=star-1 WHERE id=?";
        return star_diss_util(id, result, sql1);
    }

    private int star_diss_util(int id, int result, String sql1) {
        String sql2="SELECT star FROM t_article WHERE id=?";
        try {
            //star 数减一
            con= DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql1);
            statement.setInt(1,id);
            statement.executeUpdate();
            //获取star更新后的值
            statement=con.prepareStatement(sql2);
            statement.setInt(1,id);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                result=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateSort(String old_sort, String new_sort) {
        String sql="UPDATE t_article SET sort=? WHERE sort=?";
        int result=0;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);

            statement.setString(1,new_sort);
            statement.setString(2,old_sort);

            result=statement.executeUpdate();
            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    public boolean deleteSort(String sort) {
        String sql=null;
        int result=0;
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement;

            sql="DELETE FROM t_article WHERE sort=?";

            statement=con.prepareStatement(sql);
            statement.setString(1,sort);
            result=statement.executeUpdate();

            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result>0;
    }

    public List getPageArticle(int startIndex, int offset) {
        List<Article> list=new ArrayList<>();
        String sql="SELECT * FROM t_article ORDER BY time DESC LIMIT ?,?";
        try {
            con=DBConnection.getConnection();
            PreparedStatement statement=con.prepareStatement(sql);
            statement.setInt(1,startIndex);
            statement.setInt(2,offset);

            //查询值并 po 到 list 里
            addItToList(statement,list);

            DBUtils.close(con,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //排序
        Collections.sort(list);
        return list;
    }

    /*
     * 内部 util
     * 执行查询，并把查询结果添加到 list 里
     * @param PreparedStatement
     * @param List
     */
    private void addItToList(PreparedStatement statement,List<Article> list) throws SQLException {
        ResultSet rs=statement.executeQuery();
        while(rs.next()){
            Article article=new Article(rs.getInt("id"),rs.getString("title"),rs.getString("author"),rs.getString("sort"),
                    rs.getString("time"), rs.getInt("star"),rs.getInt("comment"),rs.getInt("visit"),rs.getString("content") );
            list.add(article);
        }
        rs.close();
    }
}
