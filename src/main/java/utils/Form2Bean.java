package utils;

import model.Article;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Form2Bean {

    public static Article form2Article(HttpServletRequest request) throws UnsupportedEncodingException {
        Article a=new Article();
        request.setCharacterEncoding("utf-8");
        String id=request.getParameter("id").trim();
        a.setId(Integer.parseInt(id));
        a.setTitle(request.getParameter("title"));
        a.setTime(request.getParameter("time"));
        a.setAuthor(request.getParameter("author"));
        a.setSort(request.getParameter("sort"));
        a.setContent(request.getParameter("mdView-markdown-doc"));
        a.setStar(0);
        a.setComment(0);
        a.setVisit(0);

        if(!validate(a))
            return null;

        return a;
    }

    private static boolean validate(Article a){
        boolean result= true;
        if(a.getSort() ==null || a.getTitle()==null || a.getAuthor()==null || a.getTime()==null){
            result = false;
        }
        return result;
    }
}
