package service;

import model.Article;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {
    AdminService instance;

    @BeforeEach
    void setUp() {
        instance=AdminService.getInstance();
    }

    @AfterEach
    void tearDown() {
        instance=null;
    }
    @Disabled
    @Test
    void updateArticle() {
        Article a=ArticleService.getInstance().getArticle("id",14+"").get(0);

    }
}