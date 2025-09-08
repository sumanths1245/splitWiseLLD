public abstract class Split {
    private double amount;
    private User paidTo;

    public Split(User paidTo) {
        this.paidTo = paidTo;
    }
    public double getAmount() {
        return this.amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidTo() {
        return this.paidTo;
    }

}
