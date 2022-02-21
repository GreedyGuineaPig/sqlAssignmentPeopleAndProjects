import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Helper {
    private final Random random = new Random();
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");

    void addNewProject(String name) {
        Project project = new Project(name);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(project);
        em.getTransaction().commit();
    }

    void addNewPerson(String name, int hourlyPay) {}

    void addNewHours(Long project_id, Long person_id, int hours) {}

    HashMap<String, Object> gatherInfo() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("projects", getAllProjects());
        map.put("persons", getAllPersons());
        map.put("hours", getAllHours());
        return map;
    }

    private List<Project> getAllProjects() {
        EntityManager em = factory.createEntityManager();
        return em.createQuery("SELECT p FROM Project p", Project.class).getResultList();
    }

    private List<Person> getAllPersons() {
        return new ArrayList<>();
    }

    private List<WorkHour> getAllHours() {
        return new ArrayList<>();
    }

    void prePopulate() {
        List<Person> people = List.of(new Person("Joe", 2), new Person("Peter", 3), new Person("Janet", 4));
        List<Project> projects = List.of(new Project("Equinox"), new Project("Sahara"), new Project("Daedalus"), new Project("Orion"));
        List<WorkHour> workhours = new ArrayList<>();
        for (Person person : people) {
            for (Project project : projects) {
                workhours.add(new WorkHour(project, person, random.nextInt(20)));
            }
        }
        // TODO: Need to persist people, projects and workhours
    }

    void deleteProject(long project_id) {
    }

    void deletePerson(long person_id) {

    }

    void deleteWorkhour(long hrs_id) {
    }
}
