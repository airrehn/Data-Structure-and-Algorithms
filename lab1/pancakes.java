import java.util.Scanner;

public class pancakes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfRestaurants = sc.nextInt();
        String output = "Anywhere is fine I guess";


        for (int i=0;i<numOfRestaurants;i++) {
            boolean flag1 = false;
            boolean flag2 = false;
            int menuNum= sc.nextInt();
            sc.nextLine();
            String restaurantname = sc.nextLine();
            for (int k=0; k<menuNum; k++) {
                    String menuitem = sc.nextLine();
                    if (menuitem.equals("pancakes")){
                        flag1 = true;
                    }
                    if (menuitem.equals("pea soup")) {
                        flag2 = true;
                    }
            }
           if ((flag1 == true) && (flag2 == true)) {
                output = restaurantname;
                //break;
           }
        }
                   
        System.out.println(output);
        sc.close();
        }
    }