package service;

import model.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest {
    CommentService instance;
    @BeforeEach
    void setUp() {
        instance=CommentService.getInstance();
    }

    @AfterEach
    void tearDown() {
        instance=null;
    }

    @Disabled
    @Test
    void loadComment() {
        List<Comment> list =instance.loadComment(12);
        assertTrue(list.size()>0);
        for(Comment c:list)
            System.out.println(c.toString());
    }

    @Disabled
    @Test
    void addComment() {
        Comment c=new Comment();
        c.setArticle_id(12);
        c.setNickname("小爱");
        c.setStar(0);
        c.setDiss(0);
        c.setTime("2019-12-23");
        c.setContent("我是机器人");
//        assertTrue(instance.addComment(c));

        List<Comment> list =instance.loadComment(12);
        for(Comment co:list)
            System.out.println(co.toString());
    }

    @Disabled
    @Test
    void star_diss() {
        assertEquals(1,instance.star(23));
    }

    @Disabled
    @Test
    void deleteComment() {
        assertTrue(instance.deleteComment(27));
    }
}