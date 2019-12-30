package service;

import dao.ArticleDao;
import daoImpl.ArticleDaoImpl;
import model.Article;
import model.AxisArticle;
import utils.ArticleUtils;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ArticleService {
    private ArticleDao dao;

    /*
     * 构造方法
     */
    private ArticleService(){
        dao= ArticleDaoImpl.getInstance();
    }
    /*
     * 获取实例
     */
    public static ArticleService getInstance(){
        return new ArticleService();
    }

    /*
     * 获取上一文章
     */
    public Article getPreviousArticle(String time){
        return dao.getANearArticle(time,ArticleDao.LESS);
    }

    /*
     * 获取下一文章
     */
    public Article getNextArticle(String time){
        return dao.getANearArticle(time,ArticleDao.MORE);
    }

    /*
     * 获取文章或分类的数量
     */
    public int getCount(String search_key){
        return dao.getCount(search_key);
    }

    /*
     * 获取用于时间轴显示的文章list
     */
    public List<AxisArticle> getAxisList(){
        List<Article> articleList=dao.getAllArticle();
        List<AxisArticle> axisList=new ArrayList<>();

        //当Jsp 页面中识别出 id=0 的特殊 axis Article 时
        //会在时间轴中输出对应的年份节点
        AxisArticle last_one=null;
        for(Article a:articleList){
            AxisArticle new_one= ArticleUtils.getAxisArticle(a);
            if(last_one==null || last_one.getYear() != new_one.getYear()){
                AxisArticle axis=new AxisArticle();
                axis.setId(0);
                axis.setYear(new_one.getYear());
                axisList.add(axis);
            }
            axisList.add(new_one);
            last_one=new_one;
        }
        return axisList;
    }

    /*
     * 通过列属性查询文章
     */
    public List<Article> getArticle(String column,String value){
        return dao.getArticleByColumn(column,value);
    }

    public Article getArticle(int id){
        return dao.getArticle(id);
    }

    /*
     * 获取目录（sort）及目录下文章的数量
     */
    public Map getSortAndCount(){
        return dao.getColumnAndCount(dao.SEARCH_SORT);
    }

    /*
     * 获取所有文章截取时间和内容后的列表
     */
    public List<Article> getAllArticle(){
        List<Article> list=dao.getAllArticle();
        ArticleUtils.cutTime(list);
        ArticleUtils.cutContent(list);
        return list;
    }

    /*
     * 获取所有sort或某一sort下的文章
     */
    public Map getSortAndArticle(String sort_name){
        Map<String,List<Article>> map=new ConcurrentHashMap<>();
        List<Article> articlesBySort=null;

        if((sort_name.equals( "all")) || StringUtils.isBlank(sort_name)){
            List<String> sorts=dao.getAllSort();
            for(String sort:sorts){
                articlesBySort=dao.getArticleByColumn("sort",sort);
                ArticleUtils.cutTime(articlesBySort);
                map.put(sort,articlesBySort);
            }
        }else{
            articlesBySort=dao.getArticleByColumn("sort",sort_name);
            ArticleUtils.cutTime(articlesBySort);
            map.put(sort_name,articlesBySort);
        }
        return map;
    }

    /*
     * 获取热门文章
     */
    public List<Article> getVisitRank(){
        List<Article> list=dao.getVisitRank();
        for(Article a:list){
            if(a.getTitle().length()>15)
                a.setTitle(StringUtils.cutString(a.getTitle(),0,12)+"...");
        }
        return list;
    }

    /*
     * 获取所有sort
     */
    public List<Article> getAllSort(){
        return dao.getAllSort();
    }

    /*
     * star 文章
     */
    public int starArticle(int id){
        return dao.star_article(id);
    }

    public int unStarArticle(int id){
        return dao.unStar_article(id);
    }
    /*
     * 点击数+1
     */
    public  void addVisit(int article_id){
        dao.addVisit(article_id);
    }

    /*
     * 获取一页文章
     */
    public List<Article> getPageArticle(int startIndex, int offset) {
        List<Article> list = dao.getPageArticle(startIndex,offset);
        ArticleUtils.cutContent(list);
        ArticleUtils.cutTime(list);
        return list;
    }
}
