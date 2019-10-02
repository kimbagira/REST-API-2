import org.sql2o.Sql2o;

public class db {

    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/rest_api_2", "wecode", "12345");
}