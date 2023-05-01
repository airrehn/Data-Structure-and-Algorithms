import java.io.*;
import java.io.IOException;
import java.util.HashMap;



public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        HashMap<Integer, TailedLinkedList> map = new HashMap<>();
        int frontKey = 1;
        int backKey = 1;
        int lines = Integer.parseInt(br.readLine());

        for (int i = 0; i<lines; i++) {
            String str = br.readLine();
            TailedLinkedList list = new TailedLinkedList();
            list.addFront(str);
            map.put(i+1, list);
        }

        for (int i = 0; i<lines-1; i++) {
            String[] line = br.readLine().split(" ");
            frontKey = Integer.parseInt(line[0]);
            backKey =  Integer.parseInt(line[1]);
            map.get(frontKey).combine(map.get(backKey));
        }


        ListNode curr = map.get(frontKey).head;
        int num_nodes = map.get(frontKey).size();
        pw.print(curr.getItem());
        for (int i=1; i <=num_nodes-1; i++) {
            curr = curr.getNext();
            pw.print(curr.getItem());
        }

        br.close();
        pw.close();
    }
}
