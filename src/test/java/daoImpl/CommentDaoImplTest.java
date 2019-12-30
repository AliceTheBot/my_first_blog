package daoImpl;


import model.Comment;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class CommentDaoImplTest {
    CommentDaoImpl dao;

    public CommentDaoImplTest(){super();}

    @BeforeEach
    public void setUp(){
        dao=(CommentDaoImpl) CommentDaoImpl.getInstance();
    }

    @AfterEach
    public void setOff(){
        dao=null;
    }

    @Disabled
    @Test
    public void deleteComment() {
        assertTrue(dao.deleteComment(24));
    }

    @Disabled
    @Test
    public void addComment(){
        Comment c=new Comment();
        c.setArticle_id(12);
        c.setContent("你也是机器人？");
        c.setNickname("Contana");
        c.setTime("2019-12-22");
        c.setDiss(0);
        c.setStar(0);
//        assertTrue(dao.addComment(c));
    }

    @Disabled
    @Test
    public void getComment(){
        List <Comment>list=dao.getComment(12);
        assertTrue(list.size()>0);
        for(Comment c:list)
            System.out.println(c.toString());
    }

    @Disabled
    @Test
    public void star_diss(){
        int star=dao.star_diss(20,Comment.STAR);
        assertTrue(star>0);
        System.out.println(star);
    }
}