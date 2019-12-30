package servlet;

import dao.ArticleDao;
import db.VisitorDB;
import model.Tag;
import org.junit.jupiter.api.Tags;
import service.ArticleService;
import service.TagService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
/*
 * TODO：看看能不能改成Jquery翻页
 * 顺便应该改名了
 */
@WebServlet("/main_page")
public class MainPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        ArticleService as = ArticleService.getInstance();

        // Article List
        int page=1;
        String strPage=req.getParameter("page");
        if(!StringUtils.isBlank(strPage))
            page=Integer.parseInt(strPage);

        int articleTotalNum=as.getCount(ArticleDao.SEARCH_ARTICLE);
        int articleNumPerPage=4;
        int pageNum=articleTotalNum % articleNumPerPage == 0 ? articleTotalNum/articleNumPerPage : articleTotalNum/articleNumPerPage+1;
        int beginIndex=(page-1)*articleNumPerPage;


        req.getSession().setAttribute("article_list", as.getAllArticle());
        req.getSession().setAttribute("totalPages",pageNum);
        req.setAttribute("page", page);
        req.setAttribute("article_pageList", as.getPageArticle(beginIndex, articleNumPerPage));

        sideInit(req,resp,as);

        //跳转主页面
        try {
            req.getRequestDispatcher("main.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    static void  sideInit(HttpServletRequest req, HttpServletResponse resp, ArticleService as){
        //Tag
        TagService ts = TagService.getInstance();
        List tags=ts.getAllTag();
        req.getSession().setAttribute("tag_list", tags);
        // Rank
        req.getSession().setAttribute("visit_rank", as.getVisitRank());
        //Sort
        Map sorts=as.getSortAndCount();
        req.getSession().setAttribute("sort_count_map", sorts);

        try {
            // visit
            req.getSession().setAttribute("clicked", VisitorDB.totalVisit());
            req.getSession().setAttribute("visited", VisitorDB.totalMember());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}