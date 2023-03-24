import java.io.*;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        String[] line = br.readLine().split(" ");
        int syllables = Integer.parseInt(line[0]);
        int numPlayers = Integer.parseInt(line[1]);
        LinkedList<Hand> list = new LinkedList<Hand>();

        for (int i = 1; i <= numPlayers; i++) {
            list.add(new Hand(i,"folded"));
        }

        while (list.size() != 1) {

            for (int i = 1; i<syllables;i++) {
                Hand hand = list.poll();
                list.offer(hand);
            }

            Hand chosenHand = list.poll();
            String str = chosenHand.getType();
            int humanNo = chosenHand.getHumanNo();

            if (str.equals("folded")){
                list.addFirst(new Hand(humanNo, "fist"));
                list.addFirst(new Hand(humanNo, "fist"));
            } else if (str.equals("fist")) {
                list.addLast(new Hand(humanNo, "palm"));
            } else {

            }

        }
        pw.print(list.poll().getHumanNo());
        br.close();
        pw.close();





    }



}    
    

