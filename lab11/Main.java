import java.io.IOException;
import java.sql.Time;
import java.util.*;
import java.io.*;

class Point {
    public  double x;
    public  double y;
    public boolean isCannon;
    
    public Point(double x, double y, boolean isCannon) {
        this.x = x;
        this.y = y;
        this.isCannon = isCannon;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isCannon() {
        return isCannon;
    }

    @Override
    public String toString() {
        return "Point is x= " + x + ", y= " + y + ", is Cannon?: " + isCannon;
    }
}

class pointTime {
    public Point start,end;
    public double time;

    public pointTime(Point start, Point end, double time) {
        this.start = start;
        this.end = end;
        this.time = time;
    }

    public Point getStartPt() {
        return start;
    }

    public Point getEndPt() {
        return end;
    }

    public double getTimeTaken() {
        return time;
    }

    @Override
    public String toString() {
        return "from: " + start + " to: " + end + " takes: " + time + "s";
    }

}


public class Main {
    public static final double inf = 1000000000.0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        ArrayList< ArrayList< pointTime > > AdjList = new ArrayList< ArrayList< pointTime > >();
        ArrayList<Double> timeArr = new ArrayList<Double>();
        ArrayList<Point> vertices = new ArrayList<Point>();
        
        //create points arr
        String[] startInpt = br.readLine().split(" ");
        Point startPoint = new Point(Double.parseDouble(startInpt[0]),Double.parseDouble(startInpt[1]), false);
        String[] endInpt = br.readLine().split(" ");
        Point endPoint = new Point(Double.parseDouble(endInpt[0]),Double.parseDouble(endInpt[1]), false);
        int noOfCannons = Integer.parseInt(br.readLine());
        vertices.add(0, startPoint);
        vertices.add(1, endPoint);
        for (int i=0;i<noOfCannons;i++) {
            String[] inpt = br.readLine().split(" ");
            Point point = new Point(Double.parseDouble(inpt[0]),Double.parseDouble(inpt[1]), true);
            vertices.add(point);
        }

        int noOfVertices = noOfCannons + 2;

        //create timeArr
        initSSSP(timeArr, noOfVertices, 0);
        // pw.println(timeArr);

        //create empty AdjList
        for (int i = 0; i < noOfVertices; i++) {
            ArrayList <pointTime> pointTimes = new ArrayList <pointTime>(); // create List of IntTriple
            AdjList.add(pointTimes); // store blank first
        } //0 indexed - 0 is the startPoint, 1 is the endPoint, and e.g. 6 is the last cannon



        for (int i=0; i<noOfVertices;i++) {
            for(int j=0; j<noOfVertices;j++) {

                    Point point1 = vertices.get(i);
                    Point point2 = vertices.get(j);
                    double dist = Math.hypot(point1.getX() - point2.getX(), point1.getY() - point2.getY());
                    double time1 = dist/5;
                    double time2= inf;
                    if (point1.isCannon()) {
                        time2 = 2 + (Math.abs(dist-50))/5;
                    }
                    AdjList.get(i).add(new pointTime(point1, point2, Math.min(time1, time2)));
                
            }
        }


        // pw.println(AdjList.get(4).get(3));


        for (int i=0;i<noOfVertices-1;i++) {
            for(int j =0; j<noOfVertices;j++) {
                for(int k=0; k<noOfVertices; k++) {
                    pointTime edge = AdjList.get(j).get(k);
                    relax(timeArr, j, k, edge.getTimeTaken());

                }
            }

        }

        pw.println(timeArr.get(1));

        
        
        br.close();
        pw.close();
    }

    public static void initSSSP(ArrayList<Double> timeArr, int noOfVertices, int s) { // initialization phase
        timeArr.addAll(Collections.nCopies(noOfVertices, inf)); // use 1B to represent inf
        timeArr.set(s, 0.0); // this is what we know so far
      }

    public static void relax (ArrayList<Double> timeArr,int u, int v, double time) {
        if(timeArr.get(u)!=inf && (timeArr.get(v) > (timeArr.get(u) + time))) {
            timeArr.set(v, timeArr.get(u) + time);
        }
    }
}

