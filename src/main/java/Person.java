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
    private List<WorkHour> workHours;

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

    public List<WorkHour> getWorkHours() { return workHours; }

    public int getTotalWorkHours() {
        int totalWorkHours = 0;
        for(WorkHour wh: workHours){
            totalWorkHours += wh.getHours();
        }
        return totalWorkHours;
    }

    public int getTotalPay() {
        int totalPay = 0;
        for(WorkHour wh: workHours){
            totalPay += wh.getHours();
        }
        return totalPay;
    }
}
