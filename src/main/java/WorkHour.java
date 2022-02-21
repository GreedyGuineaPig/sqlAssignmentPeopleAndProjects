import javax.persistence.*;

@Entity
public class WorkHour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int hours;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Project project;

    public WorkHour(Project project, Person person, int hours) {
        this.project = project;
        this.person = person;
        this.hours = hours;
    }

    private WorkHour(){}

    public long getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public Person getPerson() {
        return person;
    }

    public Project getProject() {
        return project;
    }

    public int getCost() {
        return 0;
    }
}
