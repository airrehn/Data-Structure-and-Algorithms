import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

 class IntegerPair {
    int x;
    int y;
    public IntegerPair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int first() {
        return x;
    }
    public int second() {
        return y;
    }
    
    
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int count = 0;
        String[] dimensions = br.readLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        boolean visit[][] = new boolean[rows][columns];
        for (int i=0;i<rows;i++) {
            boolean[] tempArr = new boolean[columns];
            for (int j=0;j<columns;j++) {
                tempArr[j] = false;
            }
            visit[i] = tempArr;
        }

        char[][] planet = new char[rows][columns];
        for (int i=0;i<rows;i++) {
            char[] tempArr = br.readLine().toCharArray(); 
            planet[i] = tempArr;
        }
        
        Queue<IntegerPair> queue= new LinkedList<IntegerPair>();

        for(int i=0; i<rows;i++) {
            for (int j=0; j<columns;j++) {
                char type = planet[i][j];
                IntegerPair coords = new IntegerPair(i, j);
                if (type == 'L' && visit[i][j]==false) {
                    count = count + 1;
                    queue.offer(coords);
                    visit[i][j] = true;
                    BFS(queue,planet,rows,columns, visit);

                }
            }
        }

        pw.println(count);
        br.close();
        pw.close();
        
    }

    static void BFS(Queue<IntegerPair> queue, char[][] planet, int rows, int columns, boolean[][] visit) {
        IntegerPair[] movements = new IntegerPair[4];
        movements[0] = new IntegerPair(0, -1); //up
        movements[1] = new IntegerPair(0, 1); //down
        movements[2] = new IntegerPair(1, 0); //right
        movements[3] = new IntegerPair(-1, 0); //left

        while (!queue.isEmpty()) {
            IntegerPair currCoord = queue.poll();
            for(int i =0;i<4;i++) {
                int horizontalMove = movements[i].first();
                int vertMove = movements[i].second();
                
                int nextColumn = currCoord.second() + horizontalMove;
                int nextRow = currCoord.first() + vertMove;
                if((0<=nextColumn && nextColumn<columns) && (0<=nextRow && nextRow<rows)) {
                    // System.out.println("true");
                    IntegerPair nextCoord = new IntegerPair(nextRow, nextColumn); //valid nextCoord
                    if(planet[nextRow][nextColumn] == 'L' || planet[nextRow][nextColumn] == 'C') {
                        if (visit[nextRow][nextColumn] == false) {
                            visit[nextRow][nextColumn] = true;
                            // System.out.println("queued: " + planet[nextRow][nextColumn]);
                            queue.offer(nextCoord);
                        }
                    }
                }
                else {
                    // System.out.println("out of grid");
                }

            }

        }

    }
    

    
}