package ajax;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import model.User;
import utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginAjaxServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String btn=req.getParameter("btn");
        String data=null;
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            data="false";
        }else {
            UserDao userDao = UserDaoImpl.getInstance();
            User user = null;

            if (btn.equals("log"))
                user = userDao.login(username, password);
            else
                user = userDao.register(username, password);

            if (user == null)
                data = "false";
            else {
                req.getSession().setAttribute("userName", user.getUser_name());
                req.getSession().setAttribute("power", user.getUser_power());
//                if(user.getUser_power()==1)
//                    data="bigsuccess";
//                else
                    data = "success";
            }
            resp.setContentType("text/plain;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out=resp.getWriter();
            out.print(data);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
