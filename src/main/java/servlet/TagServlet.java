package servlet;

import service.ArticleService;
import service.TagService;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/tag")
public class TagServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleService as=ArticleService.getInstance();
        TagService ts=TagService.getInstance();

        Map map=ts.getTagAndArticle("all");
        req.setAttribute("tag_art",map);

        List tags=ts.getAllTag();
        req.setAttribute("tags",tags);

        String this_tag=req.getParameter("tag");
        if(!StringUtils.isBlank(this_tag))
            req.setAttribute("this_tag",this_tag);

        MainPageServlet.sideInit(req,resp,as);

        req.getRequestDispatcher("tag.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
