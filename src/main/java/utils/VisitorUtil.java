package utils;

import db.VisitorDB;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;

public class VisitorUtil {
	private HttpServletRequest rq;
	private HttpServletResponse rp;
	
	public VisitorUtil(HttpServletRequest rq, HttpServletResponse rp){
		this.rp = rp;
		this.rq=  rq;
	}
	
	
	public  void run(){
		
		// 向数据库写入信息
		try {
			VisitorDB.visit(rq);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		rq.getSession().setAttribute("isvisit","true");
		rq.getSession().setMaxInactiveInterval(60*60);

		// 发送新的cookie
//		Cookie c = new Cookie("visitor", DateUtils.getFormatDate(new Date()));
//		// cookie生命周60分钟
//		c.setMaxAge(60 * 60);
////		c.setPath("/blog");
//		rp.addCookie(c);
	}

}
