import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("jobs/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/job-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/jobs", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("jobs", JobOpening.all());
      model.put("template", "templates/jobs.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/jobs", (request,response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String title = request.queryParams("title");
      String description = request.queryParams("description");
      String contact = request.queryParams("contact");
      int hour = Integer.parseInt(request.queryParams("hour"));
      JobOpening jobs = new JobOpening(title,description,contact, (hour+12));

      model.put("template", "templates/success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/jobs/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      JobOpening job = JobOpening.find(Integer.parseInt(request.params(":id")));
      model.put("job", job);
      model.put("template", "templates/job.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
