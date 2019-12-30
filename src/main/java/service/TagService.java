package service;

import dao.ArticleDao;
import dao.TagDao;
import daoImpl.ArticleDaoImpl;
import daoImpl.TagDaoImpl;
import model.Article;
import model.Tag;
import utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagService {
    private ArticleDao articleDao;
    private TagDao tagDao;

    private TagService(){
        articleDao=ArticleDaoImpl.getInstance();
        tagDao= TagDaoImpl.getInstance();
    }

    public static TagService getInstance(){
        return new TagService();
    }

    /*
     * 获取某文章的所有Tag
     */
    public List<Tag> getTagById(int id){
        return tagDao.getTagById(id);
    }
    /*
     * 获取所有Tag
     */
    public List<Tag> getAllTag(){
        return tagDao.getAllTag();
    }

    /*
     * 获取Tag数
     */
    public int getTagCount(){
        return tagDao.getAllTag().size();
    }

    /*
     * 获取某一标签或所有标签下的文章
     * @return Map< String TagName, List<Article> >
     */
    public Map getTagAndArticle(String tag_name){
        Map <String,List>map=new HashMap();
        List<Tag> bigTagList=null;

        if(tag_name.equals("all")|| StringUtils.isBlank(tag_name)) {
            //获取所有 tag(去重
            bigTagList = tagDao.getAllTag();
        }
        else {
            Tag tag=new Tag();
            tag.setTag(tag_name);
            tag.setId(0);
            bigTagList=new ArrayList<>();
            bigTagList.add(tag);
        }

        List<Article> articleList=null;//储存某Tag下的文章
        //遍历查找某一Tag下的文章
            for(Tag bigTag:bigTagList) {
                List<Tag> litTagList = tagDao.getTagByTag(bigTag.getTag());
                articleList = new ArrayList<>();
                for (Tag litTag : litTagList) {
                    Article a = articleDao.getArticle(litTag.getId());
                    articleList.add(a);
                }
                map.put(bigTag.getTag(), articleList);
            }
        return map;
    }


}
