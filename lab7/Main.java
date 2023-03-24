import java.util.Comparator;
import java.io.*;
import java.util.*;

class energyComparator implements Comparator<Integer> {
    public int compare(Integer n1, Integer n2) {
        if (n1 == n2) {
            return 0;
        } else if (n1 > n2) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int lines = Integer.parseInt(br.readLine());
        energyComparator energyComp = new energyComparator();
        TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<Integer, PriorityQueue<Integer>>(energyComp);

        for (int i = 0; i < lines; i++) {
            String[] input = br.readLine().split(" ");
            String cmds = input[0];
            if (cmds.equals("add")) {
                int energyInp = Integer.parseInt(input[1]);
                int goldInp = Integer.parseInt(input[2]);
                if (map.containsKey(energyInp)) {
                    PriorityQueue<Integer> PQ = map.get(energyInp);
                    PQ.offer(goldInp);
                    map.put(energyInp, PQ);
                } else {
                    PriorityQueue<Integer> goldQ = new PriorityQueue<Integer>(Collections.reverseOrder()); // change to
                                                                                                           // Max heap
                    goldQ.offer(goldInp);
                    map.put(energyInp, goldQ);
                }

            } else if (cmds.equals("query")) {
                int energyAvail = Integer.parseInt(input[1]);
                long gold = 0;
                while (true) {
                    Map.Entry<Integer,PriorityQueue<Integer>> entry = map.floorEntry(energyAvail);
                    if (entry == null) break;
                    gold = gold + entry.getValue().peek();
                    energyAvail = energyAvail - entry.getKey();
                    entry.getValue().poll();
                    if (entry.getValue().isEmpty()) {
                        map.remove(entry.getKey());
                    }

                }
                pw.println(gold);
            }

        }

        br.close();
        pw.close();

    }

}
