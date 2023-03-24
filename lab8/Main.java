import java.io.IOException;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        while(true) {
            int matrixSize = Integer.parseInt(br.readLine());
            if (matrixSize == -1)
            break;

            boolean[] isStrongLink = new boolean[matrixSize];
            for (int i =0; i<matrixSize; i++) {
                isStrongLink[i] = false;
            }

            int[][] adjMatrix =  new int[matrixSize][matrixSize];
            for (int j = 0; j < matrixSize; j++) {
                int[] tempArr = new int[matrixSize];
                String[] line = br.readLine().split(" ");
                for (int k = 0; k < matrixSize; k++) {
                    tempArr[k] = Integer.parseInt(line[k]);
                }
                adjMatrix[j] = tempArr;
            }

            for (int i=0; i<matrixSize; i++) {
                int v1 = i;
                for (int j=0; j<matrixSize; j++) {
                    int  v2 = j;
                    for (int k=0; k<matrixSize; k++) {
                        int v3 = k;
                        if ((v1 != v2) && (v2 != v3) && (v1 != v3)) {
                            if (adjMatrix[v1][v2] == 1) {
                                if (adjMatrix[v2][v3] == 1) {
                                    if (adjMatrix[v1][v3] == 1) {
                                        isStrongLink[v1] = true;
                                        isStrongLink[v2] = true;
                                        isStrongLink[v3] = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            String output = "";
            boolean firstNum = true;

            for (Integer i=0; i<matrixSize; i++) {
                if (isStrongLink[i] == false) {
                    if (firstNum == true) {
                        output += i.toString();
                        firstNum = false;
                    } else {
                        output = output + " " + i.toString();
                    }

                    
                    
                }

            }
            pw.println(output);

        }

        br.close();
        pw.close();
    }
}