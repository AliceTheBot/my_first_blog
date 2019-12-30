package service;

import dao.CommentDao;
import daoImpl.CommentDaoImpl;
import model.Comment;

import java.util.List;

public class CommentService {
    private CommentDao dao;

    private CommentService(){
        dao=CommentDaoImpl.getInstance();
    }

    public static CommentService getInstance(){
        return new CommentService();
    }

    /*
     * 获取文章的全部评论
     */
    public List loadComment(int article_id){
        return dao.getComment(article_id);
    }

    /*
     * 添加评论
     */
    public Comment addComment(Comment comment){
        return dao.addComment(comment);
    }

    /*
     * 对某一评论进行点赞或踩
     */
    public int star(int id){
        return dao.star_diss(id, Comment.STAR);
    }

    public int diss(int id){
        return dao.star_diss(id, Comment.DISS);
    }

    public int unStar(int id){
        return dao.unStar_diss(id, Comment.STAR);
    }

    public int unDiss(int id){
        return dao.unStar_diss(id, Comment.DISS);
    }
    /*
     * 删除评论
     */
    public boolean deleteComment(int id){
        return dao.deleteComment(id);
    }
}
