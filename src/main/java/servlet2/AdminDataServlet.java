package servlet2;

import service.AdminService;
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

@WebServlet("/AdminDataServlet")
public class AdminDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取操作码
		String op = request.getParameter("op");
		//初始化服务对象
		AdminService as = AdminService.getInstance();

		switch (op) {
		
		case "edit_article":
		
			String a_id1 = request.getParameter("article_id");
			request.setAttribute("edit_article", as.getArticle(Integer.parseInt(a_id1)));
			//获取分类
			ArticleService ase =  ArticleService.getInstance();		
			Map sort_count =ase.getSortAndCount();
			request.setAttribute("sort_count", sort_count);		
			//获取标签
			TagService tg= TagService.getInstance();
			List all_tag = tg.getAllTag();
			request.setAttribute("all_tag", all_tag);					
			request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
			break;
			
			
		case "delete_article":
			String a_id2 = request.getParameter("article_id");
			as.deleteArticle(Integer.parseInt(a_id2));
			break;
		
		case "sort_update":
			String old_sort = request.getParameter("old_sort");
			String new_sort = request.getParameter("new_sort");
			if(! old_sort.equals(new_sort)){
				as.updateSort(old_sort, new_sort);				
			}
			break;
		case "sort_delete":
			String sort = request.getParameter("sort");
			as.deleteSort(sort);
			break;
		case "tag_update":
			String old_tag = request.getParameter("old_tag");
			String new_tag = request.getParameter("new_tag");
			if (!old_tag.equals(new_tag)) {
				as.updateTag(old_tag, new_tag);
			}
			break;
		case "tag_delete":
			String tag = request.getParameter("tag");
			as.deleteTag(tag);
			break;
		default:
			break;

		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
