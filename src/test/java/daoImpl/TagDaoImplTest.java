package daoImpl;

import model.Tag;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TagDaoImplTest {
    TagDaoImpl dao;

    public TagDaoImplTest(){super();}

    @BeforeEach
    void setUp() {
        dao=(TagDaoImpl) TagDaoImpl.getInstance();
    }

    @AfterEach
    void tearDown() {
        dao=null;
    }

    @Disabled
    @Test
    void addTag() {
        assertTrue(dao.addTag(12,"机器人"));
    }

    @Disabled
    @Test
    void deleteTag() {
        assertTrue(dao.deleteTag(12,"机器人"));
    }

    @Disabled
    @Test
    void updateTag() {
        assertTrue(dao.updateTag("机器人","ROBOT"));
    }

    @Disabled
    @Test
    void getAllTag() {
        List<Tag> list=dao.getAllTag();
        assertTrue(list.size()>0);
        for(Tag tag:list)
            System.out.println(tag.toString());
    }

    @Disabled
    @Test
    void getTagByColumn() {
        List<Tag> list=dao.getTagById(12);
        assertTrue(list.size()>0);
        for(Tag tag:list)
            System.out.println(tag.toString());
    }
}