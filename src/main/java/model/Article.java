package model;

import java.text.ParseException;
import java.util.Date;

public class Article implements Comparable {
    private int id;
    private String title;
    private String author;
    private String sort;
    private String time;
    private int star;
    private int comment;
    private int visit;
    private String content;

    //构造函数 初始化对象
    public Article(){}
    public Article(int id,String title,String author,String sort,String time, int star,int comment,int visit,String content){
        this.id=id;
        this.title=title;
        this.author=author;
        this.time=time;
        this.sort=sort;
        this.star=star;
        this.comment=comment;
        this.visit=visit;
        this.content=content;
    }

    //与其他 article 比较 date 大小
    //如果创建时间比其他早则返回正数
    public int compareTo(Object o) {
        Article article=(Article) o;
        Date thisDate=null,otherDate=null;
        try {
            thisDate=utils.DateUtils.getDate(this.time);
            otherDate=utils.DateUtils.getDate(article.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -thisDate.compareTo(otherDate);//加负号使其递减（逆序）排序
    }

    public String toString(){
        return "Article [id=" + id + ", title=" + title + ", author=" + author + ", sort=" + sort + ", time=" + time
                + ", star=" + star + ", comment=" + comment + ", visit=" + visit + ", content=" + content + "]";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
