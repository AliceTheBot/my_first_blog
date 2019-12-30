package dao;

import model.Article;

import java.util.List;
import java.util.Map;

public interface ArticleDao {
    static final String SEARCH_ARTICLE = "article";
    static final String SEARCH_SORT = "sort";

    static final int LESS = 1;
    static final int MORE = 2;

    /*
     * 浏览量 + 1
     * @param article_id
     */
    void addVisit(int article_id);

    /*
     * 获取相邻的 前一 或 后一文章
     * @param time, less_or_more
     */
    Article getANearArticle(String time,int less_or_more);

    /*
     * 获取某一列的值和各个值的数量 返回map
     * @param search_column
     */
    Map getColumnAndCount(String search_column);

    /**
     * 返回所有的类别
     *
     * @return
     */
    List getAllSort();

    /**
     * 新的文章
     *
     * @param a
     * @return
     */
    Article addArticle(Article a);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    boolean deleteArticle(int id);

    /*
     * 修改文章
     */
    Article updateArticle(Article old_art);

    /**
     * 获取所有的文章
     *
     * @return
     */
    List getAllArticle();

    /**
     * 获取阅读排行文章列表
     *
     * @return
     */
    List getVisitRank();

    /**
     * 通过某一列查询文章
     *
     * @param column
     * @param value
     * @return
     */
    List<Article> getArticleByColumn(String column, String value);

    /*
     * 获取具体文章
     */
    public Article getArticle(int id);

    /**
     * 获取文章的数量或者类别的数量
     *
     * @param search_key
     * @return
     */
    int getCount(String search_key);

    /**
     * 点赞了文章
     *
     * @param id
     * @return
     */
    int star_article(int id);

    int unStar_article(int id);

    /**
     * 更新了类别
     *
     * @param old_sort
     * @param new_sort
     * @return
     */
    boolean updateSort(String old_sort, String new_sort);

    /**
     * 删除分类和文章
     *
     * @param sort
     * @return
     */
    boolean deleteSort(String sort);

    /*
     * 获取一页文章
     */
    List getPageArticle(int startIndex, int offset);
}
