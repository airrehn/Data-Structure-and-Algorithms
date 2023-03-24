public class Card {
    int cardType;
    int quantity;
    long buyprice;
    long sellprice;


    public Card(int cardType, int quantity, long buyprice, long sellprice) {
        this.cardType = cardType;
        this.quantity = quantity;
        this.buyprice = buyprice;
        this.sellprice = sellprice;
    }

    public Card incrQuantity() {

        return new Card(this.cardType,this.quantity+1,this.buyprice,this.sellprice);

    }


    public Card setBuySellprice(long bp, long sp) {

        return new Card(this.cardType, this.quantity, bp, sp);

    }



    public int getCardType() {
        return cardType;
    }


    public int getQuantity() {
        return quantity;
    }


    public long getBuyprice() {
        return buyprice;
    }


    public long getSellprice() {
        return sellprice;
    }

    @Override
    public String toString() {
        return "Card [cardType=" + cardType + ", quantity=" + quantity + ", buyprice=" + buyprice + ", sellprice="
                + sellprice + "]";
    }


    
    

}