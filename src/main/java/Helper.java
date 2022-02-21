import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    void addNewPerson(String name, int hourlyPay) {
        EntityManager em = factory.createEntityManager();
        Person person = new Person(name, hourlyPay);
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

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

    private List<Person> getAllPersons(){
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Person> query = builder.createQuery(Person.class);
        Root<Person> personRoot = query.from(Person.class);
        query.select(personRoot);
        return em.createQuery(query).getResultList();
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
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        for (WorkHour wh : workhours) {
            Project project = wh.getProject();
            Person person = wh.getPerson();
            em.persist(project);
            em.persist(person);
        }
        em.getTransaction().commit();
    }

    void deleteProject(long project_id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Project where id = :id")
                .setParameter("id", project_id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    void deletePerson(long person_id) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Person> criteriaDelete = builder.createCriteriaDelete(Person.class);
        Root<Person> personRoot = criteriaDelete.from(Person.class);
        criteriaDelete.where(builder.equal(personRoot.get("id"), person_id));
        em.getTransaction().begin();
        em.createQuery(criteriaDelete).executeUpdate();
        em.getTransaction().commit();
    }

    void deleteWorkhour(long hrs_id) {
    }
}
