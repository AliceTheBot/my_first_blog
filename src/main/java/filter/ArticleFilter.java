package filter;

import service.ArticleService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//点开文章 自动 增加 浏览次数
public class ArticleFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ArticleFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp=(HttpServletResponse) response;


		String id = rq.getParameter("id");		
		ArticleService as =  ArticleService.getInstance();		
		as.addVisit(Integer.parseInt(id));
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
