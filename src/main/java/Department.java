import org.sql2o.Connection;
import java.util.List;
public class Department{
    private String description;
    private String number_workers;
    private int id;
    private int Department_id;


    public <string> Department(String description, string number_workers){
        this.description = description;
        this.number_workers = (String) number_workers;
    }

    public String getdescription() {
        return description;
    }
    public String getnumber_workers() {
        return number_workers;
    }


    public void save() {
        try(Connection con = db.sql2o.open()) {
            String sql = "INSERT INTO department1(description, number_workers) VALUES (:description,:number_workers)";
            this.Department_id = (int) con.createQuery(sql, true)
                    .addParameter("description", this.description)
                    .addParameter("number_workers", this.number_workers)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }



    public List<News> getDepartment() {
        try (Connection con = (Connection) db.sql2o.open()) {
            String sql = "SELECT * FROM department1 where DepartmentId=:Department_id";
            return ((org.sql2o.Connection) con).createQuery(sql)
                    .addParameter("Department", this.Department_id)
                    .executeAndFetch(News.class);

        }
    }

    public static List<Department> all() {
        String sql = "select * from department1";
        try (Connection con = (Connection) db.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Department.class);

        }
    }

}
