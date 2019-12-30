package utils;

import model.Article;
import model.AxisArticle;

import java.util.List;

/*
 * 文章处理工具
 */
public class ArticleUtils {

    /*
     * 去掉时分秒
     */
    public static void cutTime(List<Article> list){
        for(Article a:list){
            a.setTime(a.getTime().substring(0,10));
        }
    }
    public static void cutTime(Article a){
        a.setTime(a.getTime().substring(0,10));
    }

    /*
     * 保留文章前99个字
     */
    public static void cutContent(List<Article> list){
        for(Article a:list){
            if(a.getContent().length()>100)
                a.setContent(a.getContent().substring(0,100)+"...");
        }
    }

    /*
     * Article -> AxisArticle
     */
    public static AxisArticle getAxisArticle(Article a){
        AxisArticle axis=new AxisArticle();

        axis.setId(a.getId());
        axis.setTitle(a.getTitle());

        //2019-12-22 19:53:00
        String year=StringUtils.cutString(a.getTime(),0,4);
        String month=StringUtils.cutString(a.getTime(),5,7);
        String day=StringUtils.cutString(a.getTime(),8,10);

        axis.setYear(Integer.parseInt(year));
        axis.setMonth(Integer.parseInt(month));
        axis.setDay(Integer.parseInt(day));

        return axis;
    }
}
