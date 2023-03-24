class Hand {
    int humanNo;
    String type;
    
    public Hand(int humanNo, String type) {
        this.humanNo = humanNo;
        this.type = type;
    }

    public int getHumanNo() {
        return humanNo;
    }
    public String getType() {
        return type;
    }

    public String toString() {
        return String.format("%d %s", this.humanNo, this.type);
    }

}