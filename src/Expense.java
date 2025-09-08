import java.util.List;

public abstract class Expense {
    private String id;
    private User paidBy;
    private Double amount;
    private List<Split> splits;

    public Expense(User paidBy, Double amount, List<Split> splits) {
        this.splits = splits;
        this.amount = amount;
        this.paidBy = paidBy;
    }
    public List<Split> getSplits() {
        return this.splits;
    }
    public double getAmt() {
        return this.amount;
    }
    public User getPaidBy() {
        return this.paidBy;
    }
    public abstract boolean validate();
}
