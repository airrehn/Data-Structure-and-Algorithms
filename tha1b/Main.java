import java.io.*;
import java.io.IOException;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        CardComparator cardcomp = new CardComparator();
        
        String[] fl = br.readLine().split(" ");
        int cardsInDeck = Integer.parseInt(fl[0]);
        int cardTypes = Integer.parseInt(fl[1]);
        int combos = Integer.parseInt(fl[2]);
        Card[] deck = new Card[cardTypes]; 
        for (int i=1; i<=cardTypes; i++) {
            deck[i-1] = new Card(i, 0, 0, 0);

        }

        String[] sl = br.readLine().split(" ");
        for (int i=0; i<cardsInDeck; i++) {
            deck[Integer.parseInt(sl[i]) -1] = deck[Integer.parseInt(sl[i]) -1].incrQuantity(); 
        }


        for (int i = 0; i < cardTypes ; i++) {
            String[] priceline = br.readLine().split(" ");
            deck[i] = deck[i].setBuySellprice(Long.parseLong(priceline[0]), Long.parseLong(priceline[1]));
        }

        
        Arrays.sort(deck, cardcomp);
        
        long price = 0;

        for (int i=0; i<cardTypes; i++) {

            if (i<combos) {
                price = price -  ((2-deck[i].getQuantity()) * deck[i].getBuyprice());

            } else {

                price = price + deck[i].getQuantity() * deck[i].getSellprice();
            }

        }

        
        pw.println(price);
        br.close();
        pw.close();
    }

}
