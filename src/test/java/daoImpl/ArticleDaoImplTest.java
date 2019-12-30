package daoImpl;

import dao.ArticleDao;
import model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleDaoImplTest {
    ArticleDao instance;
    @BeforeEach
    void setUp() {
        instance=ArticleDaoImpl.getInstance();
    }

    @AfterEach
    void tearDown() {
        instance=null;
    }

    @Disabled
    @Test
    void getAllArticle() {
        List <Article>list=instance.getAllArticle();
        assertTrue(list.size()>0);
        for(Article a:list)
            System.out.println(a.toString());
    }

    @Disabled
    @Test
    void updateArticle() {
        Article old_art=instance.getArticleByColumn("id","14").get(0);
        old_art.setAuthor("校园怪鸽");
        old_art.setContent("咕咕咕!");
        Article new_art=instance.updateArticle(old_art);
        assertNotNull(new_art);
        System.out.println(new_art.toString());
    }
@Disabled
    @Test
    void deleteArticle() {
        instance.deleteArticle(10);
    }
@Disabled
    @Test
    void updateSort() {
        boolean n=instance.updateSort("Java","JAVA");
        assertTrue(n);
    }
@Disabled
    @Test
    void deleteSort() {
    }
}