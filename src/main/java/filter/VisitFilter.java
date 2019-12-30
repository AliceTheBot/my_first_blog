package filter;

import utils.VisitorUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//仅统计首页jsp的访问量
//@WebFilter(filterName="VisitFilter",urlPatterns={"*.jsp"})
public class VisitFilter implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp=(HttpServletResponse) response;

		boolean visited = false;
//			Cookie[] cookies = rq.getCookies();
//			if (cookies != null) {
//				for (Cookie cookie : cookies) {
//					if (cookie.getName().equals("visitor")) {
//						visited = true;
//						break;
//					}
//				}
//			}
		String flag = (String)rq.getSession().getAttribute("isvisit");
			if(flag!=null){
				visited=true;
			}
			if (! visited) {				
	    	VisitorUtil v= new VisitorUtil(rq, rp);
	    	v.run();
			}

		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	

}
