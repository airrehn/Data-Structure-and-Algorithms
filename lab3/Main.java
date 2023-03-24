import java.io.IOException;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        NameComparator namecomp = new NameComparator();
        int numNames = Integer.parseInt(br.readLine());

        while (numNames != 0) {
            Name[] namearr = new Name[numNames];
            for (int i =0; i<numNames;i++){
                namearr[i]=new Name(br.readLine());
            }

            Arrays.sort(namearr,namecomp);

            for (int i = 0; i<numNames; i++) {
                pw.println(namearr[i]);
            }
            
            numNames = Integer.parseInt(br.readLine());

            if(numNames !=0) {
                pw.println();
            }

        }

        br.close();
        pw.close();
    }
    
}
