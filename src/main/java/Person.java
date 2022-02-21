import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable=false)
    private String name;

    @Column
    private int hourlyPay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<WorkHour> workhours;

    public Person(String name, int hourlyPay) {
        this.name = name;
        this.hourlyPay = hourlyPay;
    }

    private Person(){}

    public String getName() {
        return name;
    }

    public int getHourlyPay() {
        return hourlyPay;
    }

    public long getId() {
        return id;
    }

    public List<WorkHour> getWorkHours() {
        return workhours;
    }

    public int getTotalWorkHours() {
        return 0;
    }

    public int getTotalPay() {
        return 0;
    }
}
