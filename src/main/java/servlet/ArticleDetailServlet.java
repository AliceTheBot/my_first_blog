package servlet;

import db.VisitorDB;
import model.Article;
import model.Comment;
import model.Tag;
import service.AdminService;
import service.ArticleService;
import service.CommentService;
import service.TagService;
import utils.ArticleUtils;
import utils.CommentUtils;
import utils.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/article_detail")
public class ArticleDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleService as=ArticleService.getInstance();
        //获取请求文章的ID
        String idStr=req.getParameter("id");
        if(idStr==null) {
            resp.sendRedirect("main_page");
            return;
        }
        //获取请求文章数据
        Article article= as.getArticle(Integer.parseInt(idStr));
        if(article==null) {
            resp.sendRedirect("main_page");
            return;
        }

        req.setAttribute("this_article",article);
        //标签
        List<Tag> tags= TagService.getInstance().getTagById(article.getId());
        req.setAttribute("this_tags",tags);
        //评论
        List<Comment> comments= CommentService.getInstance().loadComment(article.getId());
        if(comments==null) {
            req.setAttribute("this_comment", "");
        }
        else {
            req.setAttribute("this_comment", comments);
        }

        //获取相邻文章
        Article preArticle=ArticleService.getInstance().getPreviousArticle(article.getTime());
        req.setAttribute("pre_article",preArticle);
        Article nextArticle=ArticleService.getInstance().getNextArticle(article.getTime());
        req.setAttribute("next_article",nextArticle);
        //获取侧边栏所需数据
        if(req.getAttribute("tag_list")==null)
            MainPageServlet.sideInit(req,resp,as);
        //跳转到详情页
        try {
            req.getRequestDispatcher("article_detail.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
