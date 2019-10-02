import java.util.ArrayList;
import java.util.List;


import org.sql2o.*;
import java.util.List;

public class News{
    private String title;
    private String content;
    private int id;

    public News ( String title,String content){
        this.title = title;
        this.content = content;

    }

    public String gettitle(){
        return title;
    }

    public String getcontent(){
        return content;
    }


    public void save() {
        try(Connection con = db.sql2o.open()) {
            String sql = "INSERT INTO news1 (title,content) VALUES (:title, :content)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("title", this.title)
                    .addParameter("content", this.content )
                    .executeUpdate()
                    .getKey();
        }
    }



    public static List<News> all() {
        String sql = "select * from news1";
        try(Connection con = db.sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(News.class);
        }
    }

    public List<News> getNews() {
        try (Connection con = (Connection) db.sql2o.open()) {
            String sql = "SELECT * FROM Department where DepartmentId=:Department_id";
            return ((org.sql2o.Connection) con).createQuery(sql)

                    .executeAndFetch(News.class);

        }
    }

}