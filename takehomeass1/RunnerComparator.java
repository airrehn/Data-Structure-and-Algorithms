import java.util.Comparator;
class RunnerComparator implements Comparator<Runner> {

    public int compare(Runner r1, Runner r2) {

        if (r1.getTime2() == r2.getTime2()) {
            return 0;
        } else if (r1.getTime2() > r2.getTime2()) {

            return 1;

        } else {

            return -1;
        }



    }
    
}
