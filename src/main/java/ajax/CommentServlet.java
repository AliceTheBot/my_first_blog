package ajax;

import model.Comment;
import org.json.JSONObject;
import service.CommentService;
import utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/add_comment")
public class CommentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int aid=Integer.parseInt(req.getParameter("aid"));
        String nickname=req.getParameter("nickname");
        String content=req.getParameter("content");
        String time= DateUtils.getFormatDate(new Date());

        Comment c0=new Comment();
        c0.setArticle_id(aid);
        c0.setNickname(nickname);
        c0.setContent(content);
        c0.setTime(time);
        c0.setStar(0);
        c0.setDiss(0);

        Comment c1= CommentService.getInstance().addComment(c0);
        JSONObject json=new JSONObject();
        if(c1==null)
            json.put("msg","failed");
        else {
            json.put("msg", "success");
            json.put("id",c1.getId());
            json.put("nickname",c1.getNickname());
            json.put("time",c1.getTime());
            json.put("content",c1.getContent());
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(json);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
