package service;

import dao.ArticleDao;
import dao.TagDao;
import daoImpl.ArticleDaoImpl;
import daoImpl.TagDaoImpl;
import model.Article;
import utils.Form2Bean;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class AdminService {
    private ArticleDao articleDao;
    private TagDao tagDao;

    private AdminService(){
        articleDao= ArticleDaoImpl.getInstance();
        tagDao= TagDaoImpl.getInstance();
    }

    public static AdminService getInstance(){
        return new AdminService();
    }

    /*
     * 添加文章
     */
    public Article addArticle(HttpServletRequest request) throws UnsupportedEncodingException {
        Article article=null;
        request.setCharacterEncoding("utf-8");

        article= Form2Bean.form2Article(request);

        if(article==null)
            return null;

        //ID是在写入数据库时自动生成的，所以要再读取出来
        article=articleDao.addArticle(article);

        //添加对应tag
        String s=request.getParameter("tags").trim();
        String[] tags=s.split(" ");
        for(String tag:tags){
            tagDao.addTag(article.getId(),tag);
        }

        return article;
    }
    /*
     * 更新文章
     */
    public Article updateArticle(HttpServletRequest request) throws UnsupportedEncodingException {
        Article old_art=Form2Bean.form2Article(request);
        return articleDao.updateArticle(old_art);
    }

    /*
     * 通过id获取文章
     */
    public Article getArticle(int article_id){
        Article a = articleDao.getArticle(article_id);
        if(a!=null){
            return a;
        }
        return null;
    }
    /*
     * 删除文章
     */
    public boolean deleteArticle(int id){
        return articleDao.deleteArticle(id);
    }

    /*
     * 更新sort
     */
    public boolean updateSort(String old_sort,String new_sort){
        return articleDao.updateSort(old_sort, new_sort);
    }

    /*
     * 删除sort
     */
    public boolean deleteSort(String sort){
        return articleDao.deleteSort(sort);
    }

    /*
     * 更新tag
     */
    public boolean updateTag(String old_tag,String new_tag){
        boolean result = tagDao.updateTag(old_tag, new_tag);
        return result;
    }

    /*
     * 删除tag
     */
    public boolean deleteTag(String tag){
        //删除所有的标签
        boolean result = tagDao.deleteTag(TagDao.DEFAULT_ID ,tag);
        return result;
    }
}
