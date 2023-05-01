import java.io.IOException;
import java.util.LinkedList;
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int testcases = Integer.parseInt(br.readLine());
        for (int a = 0; a < testcases; a++) {
            ArrayList<ArrayList<Integer>> AdjList = new ArrayList<ArrayList<Integer>>();
            Stack<Integer> toposort = new Stack<Integer>();
            ArrayList<Boolean> visited = new ArrayList<Boolean>();
            ArrayList<Boolean> knockedArr = new ArrayList<Boolean>();
            int knocked = 0;
            String[] inputs = br.readLine().split(" ");
            int dominos = Integer.parseInt(inputs[0]);
            int lines = Integer.parseInt(inputs[1]);

            // fill visited and knockedArr
            visited.addAll(Collections.nCopies(dominos, false));
            knockedArr.addAll(Collections.nCopies(dominos, false));

            // fill AdjList
            for (int i = 0; i < dominos; i++) {
                ArrayList<Integer> neighbours = new ArrayList<Integer>(); // create List of IntTriple
                AdjList.add(neighbours); // store blank first
            }

            for (int i = 0; i < lines; i++) {
                String[] inputs2 = br.readLine().split(" ");
                int source = Integer.parseInt(inputs2[0]);
                int neighbour = Integer.parseInt(inputs2[1]);
                AdjList.get(source - 1).add(neighbour - 1);
            }

            for (int i = 0; i < dominos; i++) {
                if (!visited.get(i)) {
                    DFS(i, visited, AdjList, toposort);
                }
            }

            // pw.println(AdjList.get(2));

            // while(!toposort.isEmpty()) {
            // pw.print(toposort.pop() + 1);
            // }

            while (!toposort.isEmpty()) {
                int top = toposort.pop();
                if (!knockedArr.get(top)) {
                    knocked++;
                    knockDom(top, AdjList, knockedArr, knocked);
                }
            }

            pw.println(knocked);
        }
        br.close();
        pw.close();
    }

    static void DFS(int domino, ArrayList<Boolean> visited, ArrayList<ArrayList<Integer>> AdjList,
            Stack<Integer> toposort) {
        visited.set(domino, true);
        for (int i = 0; i < AdjList.get(domino).size(); i++) {
            int neighbour = AdjList.get(domino).get(i);
            if (!visited.get(neighbour)) {
                DFS(neighbour, visited, AdjList, toposort);
            }
        }
        toposort.push(domino);
    }

    static void knockDom(int domino, ArrayList<ArrayList<Integer>> AdjList, ArrayList<Boolean> knockedArr,
            int knocked) {
        knockedArr.set(domino, true);
        for (int i = 0; i < AdjList.get(domino).size(); i++) {
            int neighbour = AdjList.get(domino).get(i);
            if (!knockedArr.get(neighbour)) {
                knockDom(neighbour, AdjList, knockedArr, knocked);
            }
        }

    }

}