package service;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TagServiceTest {
    TagService instance;
    @BeforeEach
    void setUp() {
        instance=TagService.getInstance();
    }

    @AfterEach
    void tearDown() {
        instance=null;
    }

    @Disabled
    @Test
    void getTagAndArticle() {
        Map<String, List> map=instance.getTagAndArticle(" ");
        assertTrue(map.size()>0);

        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        System.out.println();

        map=instance.getTagAndArticle("ROBOT");
        for(Map.Entry entry:map.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        System.out.println();
    }

    @Disabled
    @Test
    void getTagById() {
        List<model.Tag> list=instance.getTagById(12);
        for(model.Tag tag:list)
            System.out.println(tag.getTag());
    }
}