package utils;

import model.Comment;

import java.util.List;

public class CommentUtils {
    public static List<Comment> cutTime(List<Comment> list){
        for(Comment c:list){
            c.setTime(c.getTime().substring(0,10));
        }
        return list;
    }
}
