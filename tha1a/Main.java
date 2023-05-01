import java.io.*;
import java.io.IOException;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        RunnerComparator runnerComparator = new RunnerComparator();
        int noRunners = Integer.parseInt(br.readLine());
        ArrayList<Runner> runners = new ArrayList<>();


        for (int i = 0; i<noRunners; i++) {
            String str = br.readLine();
            String[] strarr = str.split(" ");
            String name = strarr[0];
            double time1 = Double.parseDouble(strarr[1]);
            double time2 = Double.parseDouble(strarr[2]);
            runners.add(new Runner(name, time1, time2));
        }
        


        double besttime = 0.0;
        double currtime = 0.0;
        String[] bestnames = new String[4];

        for (int i = 0; i<noRunners; i++) {
            ArrayList<Runner> tempRunners = new ArrayList<>(runners);
            String[] names = new String[4];
            names[0] = runners.get(i).getName();
            currtime = runners.get(i).getTime1();
            tempRunners.remove(i);
            Collections.sort(tempRunners, runnerComparator);

            for (int j=0; j<3; j++) {
                names[j+1] = tempRunners.get(j).getName();
                currtime = currtime + tempRunners.get(j).getTime2();
            }

            if (currtime<besttime || besttime == 0.0) {

                besttime = currtime;
                bestnames = names;

            }
        }

        pw.println(besttime);
        for (int j = 0; j<4; j++) {
            pw.println(bestnames[j]);
        }

        br.close();
        pw.close();


    }
}


 