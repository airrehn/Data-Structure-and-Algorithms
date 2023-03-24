import java.io.*;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        HashMap<ArrayList<Integer>, Integer> map = new HashMap<ArrayList<Integer>, Integer>();
        int frost = Integer.parseInt(br.readLine());

        for (int i = 0; i < frost; i++) {
            int[] courseList = new int[5];
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                courseList[j] = Integer.parseInt(line[j]);
            }

            Arrays.sort(courseList);

            ArrayList<Integer> al = new ArrayList<Integer>();
            for (int j = 0; j < 5; j++) {
                al.add(courseList[j]);
            }

            // u can only hash arrayList as keys accurately for wtv reason
            //arrays dont work because even if they have the same content, the addr is diff or something, no 2 arr are the same.
            if (map.containsKey(al)) {
                map.put(al, map.get(al) + 1);
            } else {
                map.put(al, 1);
            }
        }

        Collection<Integer> values = map.values();


        int max = 0;
        int totalWinners = 0;

        for (Integer value : values) {
            if (value > max) {
                max = value;
                totalWinners = value;
            } else if (value == max) {
                totalWinners += value;
            }
        }

        pw.println(totalWinners);

        br.close();
        pw.close();

    }
}
