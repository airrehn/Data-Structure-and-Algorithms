import java.io.*;
import java.io.IOException;
class teQueue {
    public int [] arr;
    public int frontP, backP;
    public int maxSize;
    public final int INITSIZE=1000;
    public int size;


    public teQueue() {
        arr = new int[INITSIZE];
        maxSize=INITSIZE;
        frontP = maxSize/2;
        backP = (maxSize/2)-1;
        size = 0;
    }



    public void push_front(Integer number) {

        arr[frontP] =  number;
        frontP = frontP+1;
        if (frontP == maxSize-1) 
            enlargeArr();

        size++;
    }

    public void push_back(Integer number) {
        arr[backP] = number;
        backP = backP - 1;
        if (backP == 0)
            enlargeArr();
        
        size++;
    }

    public void enlargeArr() {
        int newSize = 2 * maxSize;
        int[] temp = new int[newSize];

        for (int j=0;j<maxSize;j++) {
            temp[j+newSize/4] = arr[j];
        }

        backP = backP + newSize/4;
        frontP = frontP + newSize/4;
        arr = temp;
        maxSize = newSize;
    }


}

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int operations = Integer.parseInt(br.readLine());
        teQueue frontArray = new teQueue();
        teQueue backArray = new teQueue();

        for (int i = 0; i<operations;i++) {
            String[] line = br.readLine().split(" ");
            String instruction = line[0];
            int item = Integer.parseInt(line[1]);

            if (instruction.equals("push_back")) {
                backArray.push_front(item);
            } else if (instruction.equals("push_front")) {
                frontArray.push_back(item);
            } else if (instruction.equals("push_middle")) {
                while (java.lang.Math.abs(frontArray.size-backArray.size)>1) {
                    
                    //rebalance
                    if (frontArray.size > backArray.size) {     
                        backArray.push_back(frontArray.arr[frontArray.frontP-1]);
                        frontArray.frontP--;
                        frontArray.size--;
                        
                    } else {
                        frontArray.push_front(backArray.arr[backArray.backP+1]);
                        backArray.backP++;
                        backArray.size--;
                    } 
                             
                }

                if (backArray.size>frontArray.size) {
                    frontArray.push_front(backArray.arr[backArray.backP+1]);
                    backArray.backP++;
                    backArray.size--;
                }

                if (frontArray.size >= backArray.size) {
                    frontArray.push_front(item);
                } else {
                    backArray.push_back(item);
                }
            } else if (instruction.equals("get")) {
                if (item >= frontArray.size) {
                    int effectiveIdx = item - frontArray.size;
                    pw.println(backArray.arr[backArray.backP + 1 + effectiveIdx]);
                } else {
                    pw.println(frontArray.arr[frontArray.backP + 1 + item]);
                }

            } else {
                pw.println("invalid instruction");

            }
            
        }

        br.close();
        pw.close();
    }
}