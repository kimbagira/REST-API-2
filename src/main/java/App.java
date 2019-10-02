import java.util.List;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;


public class App{
    public static void main(String[] args){
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }
        setPort(port);

        staticFileLocation("/public");
        String layout = "/layout.hbs";


        get("/", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("templates", "user.hbs");
            return new ModelAndView(model, "user.hbs");
        }, new HandlebarsTemplateEngine ());

        get("/details", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            List<User> user=User.all();
            model.put("user", user);
            return new ModelAndView(model, "details.hbs");
        }, new HandlebarsTemplateEngine ());

        post("/details", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String employee_name = request.queryParams("employee_name");
            String position = request.queryParams("position");
            String role = request.queryParams("role");
            model.put("employee_name",employee_name);
            model.put("position",position);
            model.put("role",role);
            User newUser = new User( employee_name,position,role);
            newUser.save();
            return new ModelAndView(model, "details.hbs");
        }, new  HandlebarsTemplateEngine());



        get("/department", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            List<Department> department=Department.all();
            model.put("department", department);
            return new ModelAndView(model, "department.hbs");
        }, new HandlebarsTemplateEngine ());

        get("/details1", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "details1.hbs");
        }, new HandlebarsTemplateEngine ());

        post("/department", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String description = request.queryParams("description");
            String number_of_workers = request.queryParams("number_of_workers");
            Department departement=new Department(description,number_of_workers);
            departement.save();
            model.put("description",description);
            model.put("number_of_workers",number_of_workers);
            model.put("departement",departement);
            return new ModelAndView(model, "details1.hbs");
        }, new  HandlebarsTemplateEngine());



        get("/news", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine ());

        get("/details2", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            List<News> news=News.all();
            model.put("news", news);
            return new ModelAndView(model, "details2.hbs");
        }, new HandlebarsTemplateEngine ());

        post("/news", (request, respond) ->{
            Map<String, Object> model = new HashMap<String, Object>();
            String title = request.queryParams("title");
            String content = request.queryParams("content");
            model.put("title",title);
            model.put("content",content);
            News newNews = new News(title,content);
            newNews.save();
            return new ModelAndView(model, "details2.hbs");
        }, new  HandlebarsTemplateEngine());

    }
}