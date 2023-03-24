import java.util.Comparator;
import java.io.*;
import java.util.*;

class Person {
    int arrivalTime;
    int timeUse;


    public Person (int arrivalTime, int timeUse){
        this.arrivalTime = arrivalTime;
        this.timeUse = timeUse;
    }


    public int getArrivalTime() {
        return arrivalTime;
    }


    public int getTimeUse() {
        return timeUse;
    }
}

class arrivalComparator implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        if (p1.getArrivalTime() == p2.getArrivalTime()) {
            return 0;
        } else if (p1.getArrivalTime() > p2.getArrivalTime()) {
            return 1;
        }
        else {
            return -1;
        }
    }
}


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        arrivalComparator arrivalcomp = new arrivalComparator();
        int count = 0;
        ArrayList<Person> list = new ArrayList<>();
        PriorityQueue<Integer> inUse = new PriorityQueue<Integer>();
        PriorityQueue<Integer> unUsed = new PriorityQueue<Integer>();

        String[] input = br.readLine().split(" ");
        int numUsers = Integer.parseInt(input[0]);
        int saverTime = Integer.parseInt(input[1]);
        for (int i=0; i<numUsers;i++) {
            String[] input2 = br.readLine().split(" ");
            int arrivalTime = Integer.parseInt(input2[0]);
            int timeUse = Integer.parseInt(input2[1]); 
            list.add(new Person(arrivalTime, timeUse));
        }
        list.sort(arrivalcomp);

        for (int i=0; i<list.size();i++) {
            Person p = list.get(i);
            while ((!inUse.isEmpty()) && p.getArrivalTime() >= inUse.peek()) { //workstation currently ununsed
                unUsed.offer(inUse.poll()+saverTime);
            }

            while((!unUsed.isEmpty()) && (p.getArrivalTime() > unUsed.peek())) { //alr locked back
                unUsed.poll();
            }

            if (!unUsed.isEmpty()) {
                unUsed.poll();
                count++;
            }

            inUse.offer(p.getArrivalTime()+p.getTimeUse());

        }

        pw.println(count);
        br.close();
        pw.close();

    }
}
