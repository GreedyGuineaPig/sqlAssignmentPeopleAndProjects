import javax.persistence.*;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<WorkHour> workHours;

    public Project(String name) {
        this.name = name;
    }

    private Project() {

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
        int totalHours = 0;
        for(WorkHour wh: workHours){
            totalHours += wh.getHours();
        }
        return totalHours;
    }

    public int getTotalPay() {
        int totalPay = 0;
        for(WorkHour wh: workHours){
            totalPay += wh.getCost();
        }
        return totalPay;
    }
}
