import java.util.Scanner;
public class Canadian {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        int startIndex = line.length() -3;
        int endIndex = line.length();
        if (line.substring(startIndex, endIndex).equals("eh?")) { //for string use .equals mtd to check

            System.out.println("Canadian!");

        } else {
            System.out.println("Imposter!");
        }


    }
    
}
