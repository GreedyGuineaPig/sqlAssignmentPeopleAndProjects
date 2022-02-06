import java.util.List;

public class Project {

    private long id;

    private String name;

    private List<WorkHour> workHours;

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public List<WorkHour> getWorkHours() {
        return workHours;
    }

    public int getTotalHours() {
        return 0;
    }

    public int getTotalPay() {
        return 0;
    }
}
