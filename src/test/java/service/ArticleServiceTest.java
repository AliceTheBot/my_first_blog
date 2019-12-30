package service;

import model.Article;
import model.AxisArticle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {
    ArticleService instance;
    @BeforeEach
    void setUp() {
        instance=ArticleService.getInstance();
    }

    @AfterEach
    void tearDown() {
        instance=null;
    }

    @Disabled
    @Test
    void getAxisList() {
        List<AxisArticle> list=instance.getAxisList();
        assertTrue(list.size()>0);
        for(AxisArticle a:list)
            System.out.println(a.toString());
    }

    @Disabled
    @Test
    void getArticle() {
        List<Article> list=instance.getArticle("author","爱手艺");
        assertTrue(list.size()>0);
        for(Article a:list)
            System.out.println(a.toString());
    }

    @Disabled
    @Test
    void getSortAndCount() {
        Map <String,String>map=instance.getSortAndCount();
        assertTrue(map!=null);
        for(Map.Entry e:map.entrySet()){
            System.out.println(e.getKey()+" "+e.getValue());
        }
    }

    @Disabled
    @Test
    void testGetAllArticle() {
        List<Article> list=instance.getAllArticle();
        assertTrue(list.size()>0);
        for(Article a:list)
            System.out.println(a.toString());
    }

    @Disabled
    @Test
    void getVisitRank() {
        List<Article> list=instance.getVisitRank();
        assertTrue(list.size()>0);
        for(Article a:list)
            System.out.println(a.toString());
    }

    @Disabled
    @Test
    void getSortAndArticle() {
        Map<String,List> map=instance.getSortAndArticle("all");
        for(Map.Entry entry:map.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());

        map=instance.getSortAndArticle("怪谈");
        for(Map.Entry entry:map.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());

        assertTrue(map.size()>0);
    }

    @Disabled
    @Test
    void getPageArticle() {
        List <Article>list=instance.getPageArticle(0,4);
        assertTrue(list.size()>0);
        for(Article a:list)
            System.out.println(a.toString());
    }
}