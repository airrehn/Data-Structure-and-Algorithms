public class Runner {
    public String name;
    public double time1;
    public double time2;

    public Runner(String name, double time1, double time2) {
        this.name = name;
        this.time1 = time1;
        this.time2 = time2;

    }

    public String getName() {
        return name;
    }

    public double getTime1() {
        return time1;
    }

    public double getTime2() {
        return time2;
    }

    public String toString() {
        return name;
    }

}