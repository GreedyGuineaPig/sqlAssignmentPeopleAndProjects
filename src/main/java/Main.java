import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;

import static spark.Spark.*;

public class Main {

    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

    public static void main(String[] args) {
        exception(Exception.class, Main::printErrorMessage);
        get("/", Main::serveIndex);
        post("/addProject", Main::handleAddProject);
        post("/addPerson", Main::handleAddPerson);
        post("/addHours", Main::handleAddHours);
        post("/prePopulate", Main::handlePrepopulate);
        post("/deleteProject", Main::handleDeleteProject);
        post("/deletePerson", Main::handleDeletePerson);
        post("/deleteHours", Main::handleDeleteWorkhour);
        System.out.println("Serving on: localhost:" + port());
    }

    private static String render(Object model) {
        return engine.render(new ModelAndView(model, "index"));
    }

    private static void printErrorMessage(Exception exception, Request req, Response res) {
        System.out.println(exception.getMessage());
        exception.printStackTrace();
        res.body("Error! See the console for details.");
    }

    private static Object handleDeleteProject(Request req, Response res) {
        new Helper().deleteProject(Long.parseLong(req.queryParams("project_id")));
        res.redirect("/");
        return null;
    }

    private static Object handleDeletePerson(Request req, Response res) {
        new Helper().deletePerson(Long.parseLong(req.queryParams("person_id")));
        res.redirect("/");
        return null;
    }

    private static Object handleDeleteWorkhour(Request req, Response res) {
        new Helper().deleteWorkhour(Long.parseLong(req.queryParams("hrs_id")));
        res.redirect("/");
        return null;
    }

    private static Object handlePrepopulate(Request req, Response res) {
        new Helper().prePopulate();
        res.redirect("/");
        return null;
    }

    private static Object handleAddProject(Request req, Response res) {
        String project_name = req.queryParams("project_name");
        new Helper().addNewProject(project_name);
        res.redirect("/");
        return null;
    }

    private static Object handleAddPerson(Request req, Response res) {
        String person_name = req.queryParams("person_name");
        int hourlyPay = Integer.parseInt(req.queryParams("person_pay"));
        new Helper().addNewPerson(person_name, hourlyPay);
        res.redirect("/");
        return null;
    }

    private static Object handleAddHours(Request req, Response res) {
        Long project_id = Long.valueOf(req.queryParams("hrs_project"));
        Long person_id = Long.valueOf(req.queryParams("hrs_person"));
        int hours = Integer.parseInt(req.queryParams("hrs_hours"));
        new Helper().addNewHours(project_id, person_id, hours);
        res.redirect("/");
        return null;
    }

    private static Object serveIndex(Request req, Response res) {
        HashMap<String, Object> info = new Helper().gatherInfo();
        return render(info);
    }
}
