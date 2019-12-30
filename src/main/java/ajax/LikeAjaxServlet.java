package ajax;

import model.Comment;
import org.json.JSONObject;
import service.ArticleService;
import service.CommentService;
import utils.DateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/like")
public class LikeAjaxServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type=req.getParameter("type");
        int id=Integer.parseInt(req.getParameter("id"));
        int result=0;
        if(type.equals("art")){
            boolean flag=Boolean.parseBoolean(req.getParameter("flag"));
            //文章点赞
            if(!flag)
                result=ArticleService.getInstance().starArticle(id);
            else
                result=ArticleService.getInstance().unStarArticle(id);

            PrintWriter out=resp.getWriter();
            out.print("success\n"+result);

        }else if(type.equals("comment")){
            //对评论操作
            String op=req.getParameter("op");

            JSONObject jo = new JSONObject();
            boolean repeat = false;
            if(req.getSession().getAttribute("cm"+id)!=null){
                    //重复提交了数据
                    jo.put("msg", "failed");
                    repeat = true;
                }
            if(! repeat){
                CommentService cs = CommentService.getInstance();
                int new_num=0;
                if(op.equals("star"))
                    new_num= cs.star(id );
                else
                    new_num=cs.diss(id);

                jo.put("msg", "success");
                jo.put("new_num", new_num);

                req.getSession().setAttribute("cm"+id,"true");
            }
            //写回ajax
            resp.getWriter().println(jo);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
