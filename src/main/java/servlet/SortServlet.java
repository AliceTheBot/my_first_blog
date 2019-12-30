package servlet;

import model.Article;
import service.ArticleService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleService as=ArticleService.getInstance();

        Map map=as.getSortAndArticle("all");
        req.setAttribute("sort_art",map);

        String sort=req.getParameter("sort");
        if(!StringUtils.isBlank(sort))
            req.setAttribute("this_sort",sort);

        MainPageServlet.sideInit(req,resp,as);

        req.getRequestDispatcher("sort.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
