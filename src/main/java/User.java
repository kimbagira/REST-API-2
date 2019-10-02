import org.sql2o.Connection;
import java.util.List;


public class User{
    private String employee_name;
    private String position;
    private String role;
    private int id;


    public User(String employee_name,String position,String role){
        this.employee_name = employee_name;
        this.position = position;
        this.role = role;
    }


    public String getemployee_name() {
        return employee_name;
    }
    public String getposition(){
        return position;
    }
    public String getrole(){
        return role;
    }

    public int getid(){
        return id;
    }

    public void save() {
        try(Connection con = db.sql2o.open()) {
            String sql = "INSERT INTO user1 (employee_name,position,role) VALUES (:employee_name,:position,:role)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("employee_name", this.employee_name)
                    .addParameter("position", this.position)
                    .addParameter("role", this.role)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<User> all() {
        String sql = "select * from user1";
        try (Connection con = (Connection) db.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(User.class);

        }
    }



}
