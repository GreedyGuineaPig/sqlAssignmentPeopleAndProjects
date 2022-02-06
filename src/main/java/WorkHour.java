public class WorkHour {
    private long id;

    private int hours;

    private Person person;

    private Project project;

    public WorkHour(Project project, Person person, int hours) {
        this.project = project;
        this.person = person;
        this.hours = hours;
    }

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
