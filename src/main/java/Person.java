import java.util.List;

public class Person {
    private long id;

    private String name;

    private int hourlyPay;

    private List<WorkHour> workhours;

    public Person(String name, int hourlyPay) {
        this.name = name;
        this.hourlyPay = hourlyPay;
    }

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
