import java.util.Comparator;
class CardComparator implements Comparator<Card> {

    public int compare(Card c1, Card c2) {
        float c1score = c1.getQuantity()*c1.getSellprice() + (2-c1.getQuantity())*c1.getBuyprice(); //algorithm taken from piazza forum
        float c2score = c2.getQuantity()*c2.getSellprice() + (2-c2.getQuantity())*c2.getBuyprice();

        if (c1score == c2score) {

            return 0;

        }

        else if (c1score>c2score){

            return 1;
        }

        else {
            return -1;
        }
        



    }

    
}
