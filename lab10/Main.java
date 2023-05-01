import java.io.IOException;
import java.util.LinkedList;
import java.util.*;
import java.io.*;

class IntegerTriple implements Comparable<IntegerTriple> {
    public Integer u, v, w;
  
    public IntegerTriple(Integer u, Integer v, Integer w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }
  
    public int compareTo(IntegerTriple other) {
      if (!this.weight().equals(other.weight()))
        return this.weight() - other.weight();
      else
        return this.vertice() - other.vertice();
    }
  
    Integer weight() { 
        return w; 
    }
  
    Integer vertice() { 
        return v; 
    }

    Integer myself() {
        return u;
    }

    @Override
    public String toString() {
        return "vertice" + u + " to verticle" + v + " weight is " + w;
    }

    
  }



  public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        ArrayList<ArrayList<IntegerTriple>> AdjList = new ArrayList<ArrayList<IntegerTriple>>();
        ArrayList<Boolean> taken = new ArrayList<Boolean>();
        PriorityQueue<IntegerTriple> pq = new PriorityQueue<IntegerTriple>();
        int towns = Integer.parseInt(br.readLine());
        taken.addAll(Collections.nCopies(towns, false));
        // pw.println(taken.get(towns));
        for (int i = 0; i < towns; i++) {
            ArrayList <IntegerTriple> vertices = new ArrayList <IntegerTriple>(); // create List of IntTriple
            AdjList.add(vertices); // store blank first
        } //0 indexed

        for (int i = 0; i < towns; i++) {
            String[] weights = br.readLine().split(" ");
            for (int j = 0; j<towns;j++) {
                if (Integer.parseInt(weights[j]) != 0) {
                    AdjList.get(i).add(new IntegerTriple(i, j, Integer.parseInt(weights[j])));
                }
            }
        }
        // pw.println(AdjList.get(0).get(0));

        process(0, taken, AdjList, pq);

        while (!pq.isEmpty()) {
            IntegerTriple front = pq.poll();

            if(!taken.get(front.vertice())) {
                pw.println(String.format("%d %d", front.myself()+1, front.vertice()+1));
                taken.set(front.vertice(), true);
                process(front.vertice(), taken, AdjList, pq);
            }
        }
        


        br.close();
        pw.close();
    }

    static void process(int vertex, ArrayList<Boolean> taken,ArrayList<ArrayList<IntegerTriple>> AdjList,PriorityQueue<IntegerTriple> pq) {
        taken.set(vertex, true); //update boolean arr
        for (int i = 0; i < AdjList.get(vertex).size(); i++) { //cycle through V's adj List. Add neighbours into PQ.
          IntegerTriple temp = AdjList.get(vertex).get(i);
          if (!taken.get(temp.vertice())) {
            pq.offer(temp);  // we sort by weight then by adjacent vertex
          }
        }
      }

      
  }