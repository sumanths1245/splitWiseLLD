public class ExactSplit extends Split{
    public ExactSplit(User user, Double amt) {
        super(user);
        setAmount(amt);
    }
    public double getAmt() {
        return this.getAmount();
    }
}
