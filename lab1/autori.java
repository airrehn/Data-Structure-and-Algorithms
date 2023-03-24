import java.util.Scanner;
public class autori {
    Scanner sc = new Scanner(System.in);
    String in = sc.nextLine();
    String out = "";
    for (int i =0; i<in.length(); i++) {
        if (in.charAt(i) == in.toUpperCase().charAt(i) && Character.isLetter(in.charAt(i))) {
            out += in.chartAt(i);
        }


    }
    System.out.print(output);
    sc.close()
}
