package servlet;

import model.AxisArticle;
import service.ArticleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/time_line")
public class TimeLineServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArticleService as=ArticleService.getInstance();

        //加载时间线
        List<AxisArticle> axisArticles=as.getAxisList();
        req.setAttribute("axis_arts",axisArticles);

        //加载侧边栏数据
        MainPageServlet.sideInit(req,resp,as);

        RequestDispatcher rd=req.getRequestDispatcher("time_line.jsp");
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
