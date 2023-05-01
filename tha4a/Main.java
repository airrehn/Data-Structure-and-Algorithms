import java.io.IOException;
import java.util.LinkedList;
import java.util.*;
import java.io.*;

class IntDouble implements Comparable<IntDouble> {
    public Integer index, w;

    public IntDouble(Integer index, Integer w) {
        this.index = index;
        this.w = w;
    }

    public int compareTo(IntDouble other) {
        if (!this.ladderReq().equals(other.ladderReq()))
            return this.ladderReq() - other.ladderReq();
        else
            return this.index() - other.index();
    }

    Integer ladderReq() {
        return w;
    }

    Integer index() {
        return index;
    }

    @Override
    public String toString() {
        return "to index: " + index + " ladder req is: " + w;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        ArrayList<ArrayList<IntDouble>> AdjList = new ArrayList<ArrayList<IntDouble>>();
        ArrayList<Boolean> taken = new ArrayList<Boolean>();
        PriorityQueue<IntDouble> pq = new PriorityQueue<IntDouble>();
        String[] dimensions = br.readLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int columns = Integer.parseInt(dimensions[1]);
        int noOfStacks = rows * columns;
        int ladderReq = 0;

        // fill grid
        int[][] grid = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            int[] tempArr = new int[columns];
            String[] tempArrString = br.readLine().split(" ");
            for (int j = 0; j < columns; j++) {
                tempArr[j] = Integer.parseInt(tempArrString[j]);
            }
            grid[i] = tempArr;
        }

        // pw.println(grid[3][4]);

        // fill taken Array
        taken.addAll(Collections.nCopies(noOfStacks, false));

        // initialise empty AdjList
        for (int i = 0; i < noOfStacks; i++) {
            ArrayList<IntDouble> vertices = new ArrayList<IntDouble>(); // create List of IntTriple
            AdjList.add(vertices); // store blank first
        }

        // fill AdjList

        for (int i = 0; i < noOfStacks; i++) {
            int row = i / columns;
            int column = i % columns;

            int left = column - 1;
            if (0 <= left) { // if left exist
                int diffInHeight = grid[row][column] - grid[row][left];
                if (diffInHeight >= 0) {
                    AdjList.get(i).add(new IntDouble(i - 1, 0));
                } else { // -ve value => im lower
                    AdjList.get(i).add(new IntDouble(i - 1, Math.abs(diffInHeight)));
                }
            }

            int right = column + 1;
            if (right < columns) { // if right exist
                int diffInHeight = grid[row][column] - grid[row][right];
                if (diffInHeight >= 0) {
                    AdjList.get(i).add(new IntDouble(i + 1, 0));
                } else { // -ve value => im lower
                    AdjList.get(i).add(new IntDouble(i + 1, Math.abs(diffInHeight)));
                }
            }

            int up = row - 1;
            if (0 <= up) { // if up exist
                int diffInHeight = grid[row][column] - grid[up][column];
                if (diffInHeight >= 0) {
                    AdjList.get(i).add(new IntDouble(i - columns, 0));
                } else { // -ve value => im lower
                    AdjList.get(i).add(new IntDouble(i - columns, Math.abs(diffInHeight)));
                }
            }

            int down = row + 1;
            if (down < rows) { // if down exist
                int diffInHeight = grid[row][column] - grid[down][column];
                if (diffInHeight >= 0) {
                    AdjList.get(i).add(new IntDouble(i + columns, 0));
                } else { // -ve value => im lower
                    AdjList.get(i).add(new IntDouble(i + columns, Math.abs(diffInHeight)));
                }
            }

        }

        // pw.println(AdjList.get(33));

        process(0, ladderReq, taken, AdjList, pq);

        while (!taken.get(noOfStacks-1)) {
            IntDouble front = pq.poll();
            if(!taken.get(front.index())) {
                if (front.ladderReq()>ladderReq) {
                    ladderReq = front.ladderReq();
                }
                taken.set(front.index(), true);
                process(front.index(), ladderReq, taken, AdjList, pq);
            }
        }

        pw.println(ladderReq);
        br.close();
        pw.close();
    }

    static void process(int index, int ladderReq, ArrayList<Boolean> taken, ArrayList<ArrayList<IntDouble>> AdjList,
            PriorityQueue<IntDouble> pq) {
        taken.set(index, true);
        for (int i = 0; i < AdjList.get(index).size(); i++) {
            IntDouble temp = AdjList.get(index).get(i);
            if (!taken.get(temp.index())) {
                pq.offer(temp);
            }

        }

    }
}